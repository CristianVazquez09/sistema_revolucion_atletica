package com.wolfpack.dto;

import com.wolfpack.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InscripcionDTO {

    @EqualsAndHashCode.Include
    private Integer idInscripcion;

    private String nombre;
    private BigDecimal precio;
}
