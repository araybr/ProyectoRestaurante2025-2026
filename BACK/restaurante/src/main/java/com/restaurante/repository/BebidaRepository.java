// BebidaRepository.java
package com.restaurante.repository;

import com.restaurante.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Integer> {
}
