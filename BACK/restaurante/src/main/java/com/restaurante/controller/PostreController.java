package com.restaurante.controller;

import com.restaurante.model.Postre;
import com.restaurante.service.PostreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/postres")
@CrossOrigin(origins = "http://localhost:4200")
public class PostreController {

    private final PostreService postreService;

    public PostreController(PostreService postreService) {
        this.postreService = postreService;
    }

    @GetMapping
    public List<Postre> getAll() {
        return postreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postre> getById(@PathVariable Integer id) {
        return postreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Postre create(@RequestBody Postre postre) {
        return postreService.save(postre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postre> update(@PathVariable Integer id, @RequestBody Postre postre) {
        return postreService.findById(id)
                .map(existing -> {
                    existing.setNombre(postre.getNombre());
                    existing.setDescripcion(postre.getDescripcion());
                    existing.setPrecio(postre.getPrecio());
                    existing.setImagen_url(postre.getImagen_url());
                    return ResponseEntity.ok(postreService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        postreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
