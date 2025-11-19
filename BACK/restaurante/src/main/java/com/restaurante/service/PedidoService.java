package com.restaurante.service;

import com.restaurante.model.DetallePedido;
import com.restaurante.model.EstadoPedido;
import com.restaurante.model.Pedido;
import com.restaurante.model.Usuario;
import com.restaurante.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final DetallePedidoRepository detalleRepository;

    private final MenuRepository menuRepository;
    private final BebidaRepository bebidaRepository;
    private final PostreRepository postreRepository;


    public Pedido obtenerOCrearCarrito(Integer idUsuario) {
        return pedidoRepository.findByUsuarioIdAndEstado(idUsuario, EstadoPedido.pendiente)
                .orElseGet(() -> {
                    Usuario u = usuarioRepository.findById(idUsuario).orElseThrow();
                    Pedido carrito = new Pedido();
                    carrito.setUsuario(u);
                    carrito.setEstado(EstadoPedido.pendiente);
                    return pedidoRepository.save(carrito);
                });
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido agregarAlCarrito(Integer idUsuario, Integer idProducto, String tipo) {
        Pedido carrito = obtenerOCrearCarrito(idUsuario);
        DetallePedido detalleExistente = carrito.getDetalles().stream()
                .filter(d -> switch (tipo.toLowerCase()) {
                    case "menu" -> d.getMenu() != null && d.getMenu().getId_menu().equals(idProducto);
                    case "bebida" -> d.getBebida() != null && d.getBebida().getId_bebida().equals(idProducto);
                    case "postre" -> d.getPostre() != null && d.getPostre().getId_postre().equals(idProducto);
                    default -> false;
                })
                .findFirst()
                .orElse(null);

        if (detalleExistente != null) {
            detalleExistente.setCantidad(detalleExistente.getCantidad() + 1); // sumar 1
        } else {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(carrito);

            switch (tipo.toLowerCase()) {
                case "menu" -> detalle.setMenu(menuRepository.findById(idProducto).orElseThrow());
                case "bebida" -> detalle.setBebida(bebidaRepository.findById(idProducto).orElseThrow());
                case "postre" -> detalle.setPostre(postreRepository.findById(idProducto).orElseThrow());
            }

            detalle.setCantidad(1);
            carrito.getDetalles().add(detalle);
        }

        return pedidoRepository.save(carrito);
    }

    public void actualizarCantidadDetalle(Integer idDetalle, Integer cantidad) {
        DetallePedido detalle = detalleRepository.findById(idDetalle)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalle.setCantidad(cantidad);
        detalleRepository.save(detalle);
    }


    // Finalizar pedido
    public Pedido finalizarPedido(Integer idUsuario) {
        Pedido carrito = obtenerOCrearCarrito(idUsuario);
        carrito.setEstado(EstadoPedido.entregado);
        return pedidoRepository.save(carrito);
    }

    @Transactional
    public void eliminarDetalle(Integer idDetalle) {
        DetallePedido detalle = detalleRepository.findById(idDetalle)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        Pedido pedido = detalle.getPedido();
        pedido.getDetalles().remove(detalle); // <-- sincroniza la relación
        detalleRepository.delete(detalle);
    }

}
