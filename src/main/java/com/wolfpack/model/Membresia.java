package com.wolfpack.model;

import com.wolfpack.model.enums.TipoMovimiento;
import com.wolfpack.model.enums.TipoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idMembresia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_socio", nullable = false, foreignKey = @ForeignKey(name = "FK_MEMBRESIA_SOCIO"))
    private Socio socio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paquete", nullable = false, foreignKey = @ForeignKey(name = "FK_MEMBRESIA_PAQUETE"))
    private Paquete paquete;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMovimiento movimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoPago tipoPago;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal descuento;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal total;



}
