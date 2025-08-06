package com.wolfpack.dto.membresia;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wolfpack.dto.PaqueteDTO;
import com.wolfpack.dto.SocioDTO;
import com.wolfpack.model.Paquete;
import com.wolfpack.model.Socio;
import com.wolfpack.model.enums.TipoMovimiento;
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
public class MembresiaDTO {

    @EqualsAndHashCode.Include
    private Long idMembresia;
    private SocioDTO socio;
    private PaqueteDTO paquete;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private TipoMovimiento movimiento;
    private TipoPago tipoPago;
    private BigDecimal descuento;
    private BigDecimal total;

}
