package com.wolfpack.controller;

import com.wolfpack.dto.InscripcionDTO;
import com.wolfpack.model.Inscripcion;
import com.wolfpack.service.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {
      
    private final IInscripcionService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> buscarTodos() throws Exception{
        List<InscripcionDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        Inscripcion obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody InscripcionDTO dto) throws Exception{
        Inscripcion obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInscripcion()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> actualizar( @PathVariable("id") Integer id, @RequestBody InscripcionDTO dto) throws Exception{
        dto.setIdInscripcion(id);
        Inscripcion obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private InscripcionDTO convertirADto(Inscripcion obj){
        return modelMapper.map(obj, InscripcionDTO.class);
    }

    private Inscripcion convertirAEntidad(InscripcionDTO dto){
        return modelMapper.map(dto, Inscripcion.class);
    }



}
