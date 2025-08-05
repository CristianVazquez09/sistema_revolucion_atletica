package com.wolfpack.model;

import com.wolfpack.model.enums.TiempoPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ZonaCombate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idZonaCombate;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TiempoPlan tiempo;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_entrenador", nullable = false, foreignKey = @ForeignKey(name = "FK_ZONA_ENTRENADOR"))
    private Entrenador entrenador;


    @PrePersist
    public void asignarEstado() {
        this.activo = true;
    }
}
