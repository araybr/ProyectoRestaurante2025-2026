package com.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
