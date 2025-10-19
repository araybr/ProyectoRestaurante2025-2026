// UsuarioRepository.java
package com.restaurante.repository;

import com.restaurante.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
