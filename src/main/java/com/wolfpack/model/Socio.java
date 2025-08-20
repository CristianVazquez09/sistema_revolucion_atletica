package com.wolfpack.model;

import com.wolfpack.model.enums.Genero;
import com.wolfpack.multitenancy.jpa.TenantScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Socio  extends TenantScoped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idSocio;

    @Column(length = 40, nullable = false)
    private String nombre;

    @Column(length = 40, nullable = false)
    private String apellido;

    @Column(length = 150, nullable = false)
    private String direccion;

    @Column(length = 11, nullable = false, unique = true)
    private String telefono;

    @Column(length = 150, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    //private String fotoUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Genero genero;

    //private byte[] huellaDigital;
    @Lob
    private String comentarios;


    @Column(nullable = false)
    private boolean activo;

    @PrePersist
    public void asignarEstado() {
        this.activo = true;
    }

}
