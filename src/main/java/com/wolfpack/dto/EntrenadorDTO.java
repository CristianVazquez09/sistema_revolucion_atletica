package com.wolfpack.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EntrenadorDTO {

    @EqualsAndHashCode.Include
    private Integer idEntrenador;

    private String nombre;

    private String apellido;

}
