package com.wolfpack.controller;

import com.wolfpack.dto.membresia.MembresiaDTO;
import com.wolfpack.model.Membresia;
import com.wolfpack.service.IMembresiaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/membresias")
@RequiredArgsConstructor
public class MembresiaController {
    //@Autowired
    private final IMembresiaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MembresiaDTO>> buscarTodos() throws Exception{
        List<MembresiaDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        Membresia obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody MembresiaDTO dto) throws Exception{
        Membresia obj = service.guardarMembresia(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMembresia()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDTO> actualizar( @PathVariable("id") Long id, @RequestBody MembresiaDTO dto) throws Exception{
        dto.setIdMembresia(id);
        Membresia obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private MembresiaDTO convertirADto(Membresia obj){
        return modelMapper.map(obj, MembresiaDTO.class);
    }

    private Membresia convertirAEntidad(MembresiaDTO dto){
        return modelMapper.map(dto, Membresia.class);
    }



}
