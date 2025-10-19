package com.restaurante.service;


import com.restaurante.model.Valoracion;
import com.restaurante.repository.ValoracionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;

    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<Valoracion> getAllValoraciones() {
        return valoracionRepository.findAll();
    }

    public Optional<Valoracion> findById(int id) {
        return valoracionRepository.findById(id);
    }

    public Valoracion saveValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public void deleteById(int id) {
        valoracionRepository.deleteById(id);
    }
}

