// MenuRepository.java
package com.restaurante.repository;

import com.restaurante.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
