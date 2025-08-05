package com.wolfpack.model;

import com.wolfpack.model.enums.TipoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idVenta;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_VENTA_USUARIO"))
    private Usuario usuario;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<DetalleVenta> detalles;



    @PrePersist
    public void asignarFechaIngreso() {
        this.activo = true;
        this.fecha = LocalDateTime.now();
    }

}
