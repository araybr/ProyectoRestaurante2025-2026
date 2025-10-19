// ValoracionRepository.java
package com.restaurante.repository;

import com.restaurante.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
}
