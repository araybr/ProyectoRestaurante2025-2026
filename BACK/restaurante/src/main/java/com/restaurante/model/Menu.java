package com.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_menu;

    private String nombre;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Categoria categoria = Categoria.principal;

    private BigDecimal precio;
    private String imagen_url;
    private Boolean especialidad = false;
    private Boolean mas_vendido = false;
    private BigDecimal valoracion_media = BigDecimal.ZERO;

    @ManyToMany
    @JoinTable(
            name = "Menu_Ingrediente",
            joinColumns = @JoinColumn(name = "id_menu"),
            inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private Set<Ingrediente> ingredientes;
}


