package com.restaurante.controller;

import com.restaurante.model.Valoracion;
import com.restaurante.repository.ValoracionRepository;
import com.restaurante.service.ValoracionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ValoracionController {

    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    @GetMapping
    public List<Valoracion> getValoraciones() {
        return valoracionService.getAllValoraciones();
    }

    @PostMapping
    public Valoracion addValoracion(@RequestBody Valoracion valoracion) {
        return valoracionService.saveValoracion(valoracion);
    }
}

