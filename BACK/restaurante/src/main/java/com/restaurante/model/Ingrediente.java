// Ingrediente.java
package com.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ingrediente;

    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    @JsonIgnore
    private Set<Menu> menus;

    @ManyToMany(mappedBy = "ingredientes")
    @JsonIgnore
    private Set<Postre> postres;

    @ManyToMany(mappedBy = "ingredientes")
    @JsonIgnore
    private Set<Bebida> bebidas;

    @ManyToMany
    @JoinTable(
            name = "Ingrediente_Alergeno",
            joinColumns = @JoinColumn(name = "id_ingrediente"),
            inverseJoinColumns = @JoinColumn(name = "id_alergeno")
    )
    private Set<Alergeno> alergenos;
}
