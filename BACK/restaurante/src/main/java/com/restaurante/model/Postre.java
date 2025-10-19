// Postre.java
package com.restaurante.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Postre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_postre;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagen_url;
    private BigDecimal valoracion_media = BigDecimal.ZERO;

    @ManyToMany
    @JoinTable(
            name = "Postre_Ingrediente",
            joinColumns = @JoinColumn(name = "id_postre"),
            inverseJoinColumns = @JoinColumn(name = "id_ingrediente")
    )
    private Set<Ingrediente> ingredientes;
}
