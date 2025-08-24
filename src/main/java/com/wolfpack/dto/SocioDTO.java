package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfpack.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SocioDTO {

    @EqualsAndHashCode.Include
    private Long idSocio;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;

    private String email;

    private LocalDate fechaNacimiento;

    private Genero genero;

    private String comentarios;

    /*
    private byte[] huellaDigital;
    private String fotoUrl;
    */

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean activo;
}
