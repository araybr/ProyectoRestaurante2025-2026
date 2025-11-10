package com.restaurante.service;

import com.restaurante.model.DetallePedido;
import com.restaurante.model.EstadoPedido;
import com.restaurante.model.Pedido;
import com.restaurante.model.Usuario;
import com.restaurante.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePedidoRepository detalleRepository;
    private final MenuRepository menuRepository;
    private final BebidaRepository bebidaRepository;
    private final PostreRepository postreRepository;

    // Obtener el carrito activo o crear uno nuevo
    public Pedido obtenerOCrearCarrito(Integer idUsuario) {
        return pedidoRepository.findByUsuarioIdAndEstado(idUsuario, EstadoPedido.PENDIENTE)
                .orElseGet(() -> {
                    Usuario u = usuarioRepository.findById(idUsuario).orElseThrow();
                    Pedido carrito = new Pedido();
                    carrito.setUsuario(u);
                    carrito.setEstado(EstadoPedido.PENDIENTE);
                    return pedidoRepository.save(carrito);
                });
    }

    // Agregar producto al carrito
    public Pedido agregarAlCarrito(Integer idUsuario, Integer idProducto, String tipo) {
        Pedido carrito = obtenerOCrearCarrito(idUsuario);
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(carrito);

        switch (tipo.toLowerCase()) {
            case "menu" -> detalle.setMenu(menuRepository.findById(idProducto).orElseThrow());
            case "bebida" -> detalle.setBebida(bebidaRepository.findById(idProducto).orElseThrow());
            case "postre" -> detalle.setPostre(postreRepository.findById(idProducto).orElseThrow());
        }

        detalle.setCantidad(1); // por defecto 1
        carrito.getDetalles().add(detalle);

        return pedidoRepository.save(carrito);
    }

    // Finalizar pedido
    public Pedido finalizarPedido(Integer idUsuario) {
        Pedido carrito = obtenerOCrearCarrito(idUsuario);
        carrito.setEstado(EstadoPedido.PREPARACION);
        return pedidoRepository.save(carrito);
    }
}
