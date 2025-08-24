package com.wolfpack.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioRequestDTO {

    @EqualsAndHashCode.Include
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private boolean activo;

    private String rol;

}
