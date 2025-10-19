package com.restaurante.service;

import com.restaurante.model.Bebida;
import com.restaurante.model.Ingrediente;
import com.restaurante.repository.BebidaRepository;
import com.restaurante.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public List<Bebida> findAll() {
        return bebidaRepository.findAll();
    }

    public Optional<Bebida> findById(Integer id) {
        return bebidaRepository.findById(id);
    }

    public Bebida save(Bebida bebida) {
        return bebidaRepository.save(bebida);
    }

    public void deleteById(Integer id) {
        bebidaRepository.deleteById(id);
    }
}
