package com.restaurante.controller;

import com.restaurante.model.Bebida;
import com.restaurante.service.BebidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
@CrossOrigin(origins = "http://localhost:4200")
public class BebidaController {

    private final BebidaService bebidaService;

    public BebidaController(BebidaService bebidaService) {
        this.bebidaService = bebidaService;
    }

    @GetMapping
    public List<Bebida> getAll() {
        return bebidaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bebida> getById(@PathVariable Integer id) {
        return bebidaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bebida create(@RequestBody Bebida bebida) {
        return bebidaService.save(bebida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> update(@PathVariable Integer id, @RequestBody Bebida bebida) {
        return bebidaService.findById(id)
                .map(existing -> {
                    existing.setNombre(bebida.getNombre());
                    existing.setDescripcion(bebida.getDescripcion());
                    existing.setPrecio(bebida.getPrecio());
                    existing.setImagen_url(bebida.getImagen_url());
                    return ResponseEntity.ok(bebidaService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bebidaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
