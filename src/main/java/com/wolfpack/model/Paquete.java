package com.wolfpack.model;

import com.wolfpack.model.enums.TiempoPlan;
import com.wolfpack.util.TenantScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Paquete  extends TenantScoped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPaquete;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TiempoPlan tiempo;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal costoInscripcion;

    @Column(nullable = false)
    private boolean activo;


    @PrePersist
    public void asignarEstado() {
        this.activo = true;
    }
}
