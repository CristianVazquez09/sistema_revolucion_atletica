package com.wolfpack.dto;

import com.wolfpack.model.enums.TiempoPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaqueteDTO {

    @EqualsAndHashCode.Include
    private Integer idPaquete;
    private String nombre;
    private BigDecimal precio;
    private TiempoPlan tiempo;
    private BigDecimal costoInscripcion;

}
