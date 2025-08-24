package com.wolfpack.dto.usuario;

import com.wolfpack.dto.RolDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioResponseDTO {

    @EqualsAndHashCode.Include
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private boolean activo;

    private RolDTO rol;

}
