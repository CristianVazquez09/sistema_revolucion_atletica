package com.wolfpack.model;

import com.wolfpack.model.enums.TipoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false, foreignKey = @ForeignKey(name = "FK_MEMBRESIA_SOCIO"))
    private Socio socio;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @OneToMany(mappedBy = "membresia", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<MembresiaServicio> servicios;

    @PrePersist
    public void asignarFechaIngreso() {
        this.fechaIngreso = LocalDate.now();
    }



}
