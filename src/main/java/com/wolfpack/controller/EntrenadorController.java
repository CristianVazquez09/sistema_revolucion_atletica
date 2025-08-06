package com.wolfpack.controller;

import com.wolfpack.dto.EntrenadorDTO;
import com.wolfpack.model.Entrenador;
import com.wolfpack.service.IEntrenadorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {
    //@Autowired
    private final IEntrenadorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EntrenadorDTO>> BuscarTodos() throws Exception{
        List<EntrenadorDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        Entrenador obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody EntrenadorDTO dto) throws Exception{
        Entrenador obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEntrenador()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> actualizar( @PathVariable("id") Integer id, @RequestBody EntrenadorDTO dto) throws Exception{
        dto.setIdEntrenador(id);
        Entrenador obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private EntrenadorDTO convertirADto(Entrenador obj){
        return modelMapper.map(obj, EntrenadorDTO.class);
    }

    private Entrenador convertirAEntidad(EntrenadorDTO dto){
        return modelMapper.map(dto, Entrenador.class);
    }



}
