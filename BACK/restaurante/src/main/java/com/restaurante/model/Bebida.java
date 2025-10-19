// Bebida.java
package com.restaurante.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.math.BigDecimal;

@Entity
@Table(name = "Bebida")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_bebida;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagen_url;

    @ManyToMany
    @JoinTable(
            name = "Bebida_Ingrediente",
            joinColumns = @JoinColumn(name = "id_bebida"),
            inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private Set<Ingrediente> ingredientes;
}
