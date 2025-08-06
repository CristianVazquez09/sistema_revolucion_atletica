package com.wolfpack.controller;

import com.wolfpack.dto.usuario.UsuarioRequestDTO;
import com.wolfpack.dto.usuario.UsuarioResponseDTO;
import com.wolfpack.model.Usuario;
import com.wolfpack.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    //@Autowired
    private final IUsuarioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodos() throws Exception{
        List<UsuarioResponseDTO> list = service.buscarTodos().stream().map(this::convertirADtoResponse).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        Usuario obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADtoResponse(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody UsuarioRequestDTO dto) throws Exception{
        Usuario obj = service.guardarNuevoUsuario(convertirAEntidad(dto), dto.getRol());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable("id") Integer id, @RequestBody UsuarioRequestDTO dto) throws Exception{
        dto.setIdUsuario(id);
        Usuario obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADtoResponse(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private UsuarioResponseDTO convertirADtoResponse(Usuario obj){
        return modelMapper.map(obj, UsuarioResponseDTO.class);
    }

    private Usuario convertirAEntidad(UsuarioRequestDTO dto){
        return modelMapper.map(dto, Usuario.class);
    }



}
