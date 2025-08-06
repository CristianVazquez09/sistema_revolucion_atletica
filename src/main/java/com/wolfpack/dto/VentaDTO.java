package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wolfpack.dto.usuario.UsuarioRequestDTO;
import com.wolfpack.dto.usuario.UsuarioResponseDTO;
import com.wolfpack.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VentaDTO {

    @EqualsAndHashCode.Include
    private Long idVenta;

    private LocalDateTime fecha;
    private BigDecimal total;
    private TipoPago tipoPago;

    @JsonManagedReference
    private List<DetalleVentaDTO> detalles;

    private UsuarioResponseDTO usuario;


}
