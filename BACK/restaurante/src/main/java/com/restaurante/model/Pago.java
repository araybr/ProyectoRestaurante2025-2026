package com.restaurante.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    private BigDecimal monto;
    private LocalDateTime fecha_pago = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private MetodoPago metodo;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado = EstadoPago.PENDIENTE;
}

enum EstadoPago { PENDIENTE, COMPLETADO, FALLIDO }
