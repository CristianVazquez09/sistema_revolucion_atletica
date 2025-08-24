package com.wolfpack.model;

import com.wolfpack.multitenancy.jpa.TenantScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Usuario  extends TenantScoped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(nullable = false, length = 60, unique = true)
    private String nombreUsuario;

    @Column(nullable = false, length = 60)
    private String contrasenia;

    @Column(nullable = false)
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_ROL"))
    private Rol rol;

    @PrePersist
    public void asignarEstado() {
        this.activo = true;
    }

}
