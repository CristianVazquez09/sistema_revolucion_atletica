package com.wolfpack.dto;

import com.wolfpack.dto.usuario.UsuarioResponseDTO;
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
public class AccesoriaPersonalizadaDTO {

    @EqualsAndHashCode.Include
    private Long idAccesoriaPersonalizada;

    private BigDecimal precio;

    private TiempoPlan tiempo;

    private EntrenadorDTO entrenador;

    private SocioDTO socio;
    private UsuarioResponseDTO usuario;

}
