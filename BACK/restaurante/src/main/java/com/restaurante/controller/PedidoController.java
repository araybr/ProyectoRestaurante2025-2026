package com.restaurante.controller;

import com.restaurante.model.Menu;
import com.restaurante.model.Pedido;
import com.restaurante.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;


    @GetMapping
    public List<Pedido> getAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/carrito/{usuarioId}")
    public Pedido obtenerCarrito(@PathVariable Integer usuarioId) {
        return pedidoService.obtenerOCrearCarrito(usuarioId);
    }

    @PostMapping("/carrito/{usuarioId}/agregar")
    public Pedido agregarAlCarrito(@PathVariable Integer usuarioId,
                                   @RequestParam Integer idProducto,
                                   @RequestParam String tipo) {
        return pedidoService.agregarAlCarrito(usuarioId, idProducto, tipo);
    }

    @PostMapping("/carrito/{usuarioId}/finalizar")
    public Pedido finalizarPedido(@PathVariable Integer usuarioId) {
        return pedidoService.finalizarPedido(usuarioId);
    }
}

