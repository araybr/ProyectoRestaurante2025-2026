package com.restaurante.controller;

import com.restaurante.model.Usuario;
import com.restaurante.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario user) {
        Usuario u = usuarioService.findByEmail(user.getEmail()).orElse(null);
        if (u != null && u.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario user) {
        if (usuarioService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Usuario nuevo = usuarioService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.findById(id)
                .map(existing -> {
                    existing.setNombre(usuario.getNombre());
                    existing.setApellidos(usuario.getApellidos());
                    existing.setEmail(usuario.getEmail());
                    existing.setPassword(usuario.getPassword());
                    existing.setTelefono(usuario.getTelefono());
                    existing.setRol(usuario.getRol());
                    existing.setActivo(usuario.getActivo());
                    return ResponseEntity.ok(usuarioService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
