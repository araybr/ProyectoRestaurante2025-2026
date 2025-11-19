package com.restaurante.controller;

import com.restaurante.model.Menu;
import com.restaurante.model.Pedido;
import com.restaurante.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/detalle/{idDetalle}")
    public ResponseEntity<?> actualizarDetalle(
            @PathVariable Integer idDetalle,
            @RequestBody Map<String, Integer> body // recibimos JSON
    ) {
        try {
            Integer cantidad = body.get("cantidad");
            pedidoService.actualizarCantidadDetalle(idDetalle, cantidad); // método en tu service
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Cantidad actualizada correctamente",
                    "idDetalle", idDetalle,
                    "cantidad", cantidad
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "No se encontró el detalle con ID: " + idDetalle
                    ));
        }
    }

    @DeleteMapping("/detalle/{idDetalle}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Integer idDetalle) {
        try {
            pedidoService.eliminarDetalle(idDetalle);

            // ✅ Devolvemos un JSON en vez de texto plano
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Detalle eliminado correctamente",
                    "idDetalle", idDetalle
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "No se encontró el detalle con ID: " + idDetalle
                    ));
        }
    }



    @PostMapping("/carrito/{usuarioId}/finalizar")
    public Pedido finalizarPedido(@PathVariable Integer usuarioId) {
        return pedidoService.finalizarPedido(usuarioId);
    }
}

