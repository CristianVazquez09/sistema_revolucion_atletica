package com.wolfpack.dto.membresia;

import com.wolfpack.dto.PaqueteDTO;
import com.wolfpack.dto.SocioDTO;
import com.wolfpack.model.enums.TipoMovimiento;
import com.wolfpack.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MembresiaRequestDTO {

    @EqualsAndHashCode.Include
    private Long idMembresia;
    private SocioDTO socio;
    private PaqueteDTO paquete;
    private TipoMovimiento movimiento;
    private TipoPago tipoPago;
    private BigDecimal descuento;

}
