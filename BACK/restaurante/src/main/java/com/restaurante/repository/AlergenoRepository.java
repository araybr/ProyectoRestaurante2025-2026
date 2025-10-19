// AlergenoRepository.java
package com.restaurante.repository;

import com.restaurante.model.Alergeno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlergenoRepository extends JpaRepository<Alergeno, Integer> {
}
