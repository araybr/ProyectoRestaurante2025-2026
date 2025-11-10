// PedidoRepository.java
package com.restaurante.repository;

import com.restaurante.model.EstadoPedido;
import com.restaurante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByUsuarioIdAndEstado(Integer idUsuario, EstadoPedido estado);
}
