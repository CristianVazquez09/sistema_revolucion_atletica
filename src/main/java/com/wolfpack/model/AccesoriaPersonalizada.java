package com.wolfpack.model;

import com.wolfpack.model.enums.TiempoPlan;
import com.wolfpack.util.TenantScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class AccesoriaPersonalizada extends TenantScoped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAccesoriaPersonalizada;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TiempoPlan tiempo;
    
    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_entrenador", nullable = false, foreignKey = @ForeignKey(name = "FK_ACCESORIA_ENTRENADOR"))
    private Entrenador entrenador;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false, foreignKey = @ForeignKey(name = "FK_ACCESORIA_SOCIO"))
    private Socio socio;

    @PrePersist
    public void asignarEstado() {
        this.activo = true;
    }


}
