package com.restaurante.service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.model.Menu;
import com.restaurante.repository.MenuRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll() {
       return menuRepository.findAll();
    }

    public Optional<Menu> findById(Integer id) {
        return menuRepository.findById(id);
    }

    public Menu save(Menu ingrediente) {
        return menuRepository.save(ingrediente);
    }

    public void deleteById(Integer id) {
        menuRepository.deleteById(id);
    }
}

