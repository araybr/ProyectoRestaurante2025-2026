// PagoRepository.java
package com.restaurante.repository;

import com.restaurante.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
