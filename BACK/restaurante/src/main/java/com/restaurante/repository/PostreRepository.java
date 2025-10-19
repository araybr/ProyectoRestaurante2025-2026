// PostreRepository.java
package com.restaurante.repository;

import com.restaurante.model.Postre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostreRepository extends JpaRepository<Postre, Integer> {
}
