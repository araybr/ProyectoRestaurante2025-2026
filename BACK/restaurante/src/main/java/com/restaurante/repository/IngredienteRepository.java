// IngredienteRepository.java
package com.restaurante.repository;

import com.restaurante.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
