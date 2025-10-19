package com.restaurante.model;

import jakarta.persistence.*;

// Direccion.java
@Entity
@Table(name = "Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_direccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
    private String cp;
    private Boolean principal = false;
}
