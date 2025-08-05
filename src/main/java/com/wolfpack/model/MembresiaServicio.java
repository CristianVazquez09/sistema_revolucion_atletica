package com.wolfpack.model;

import com.wolfpack.model.enums.TipoMovimiento;
import com.wolfpack.model.enums.TipoPago;
import com.wolfpack.model.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MembresiaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idMembresiaServicio;

    @ManyToOne
    @JoinColumn(name = "id_membresia", nullable = false, foreignKey = @ForeignKey(name = "FK_SERVICIO_MEMBRESIA"))
    private Membresia membresia;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoServicio tipoServicio;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal descuento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMovimiento movimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoPago tipoPago;

    @Column(nullable = false)
    private Long idServicio;



}
