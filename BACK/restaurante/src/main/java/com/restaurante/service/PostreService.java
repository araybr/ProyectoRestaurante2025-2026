package com.restaurante.service;

import com.restaurante.model.Menu;
import com.restaurante.model.Postre;
import com.restaurante.repository.PostreRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostreService {
    private final PostreRepository postreRepository;

    public PostreService(PostreRepository postreRepository) {
        this.postreRepository = postreRepository;
    }

    public List<Postre> getAllMenus() {
        return postreRepository.findAll();
    }
    public List<Postre> findAll() {
        return postreRepository.findAll();
    }

    public Optional<Postre> findById(Integer id) {
        return postreRepository.findById(id);
    }

    public Postre save(Postre postre) {
        return postreRepository.save(postre);
    }

    public void deleteById(Integer id) {
        postreRepository.deleteById(id);
    }
}

