package com.wolfpack.controller;

import com.wolfpack.dto.PaqueteDTO;
import com.wolfpack.model.Paquete;
import com.wolfpack.service.IPaqueteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/paquetes")
@RequiredArgsConstructor
public class PaqueteController {
    //@Autowired
    private final IPaqueteService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PaqueteDTO>> buscarTodos() throws Exception{
        List<PaqueteDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        Paquete obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody PaqueteDTO dto) throws Exception{
        Paquete obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPaquete()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaqueteDTO> actualizar( @PathVariable("id") Integer id, @RequestBody PaqueteDTO dto) throws Exception{
        dto.setIdPaquete(id);
        Paquete obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private PaqueteDTO convertirADto(Paquete obj){
        return modelMapper.map(obj, PaqueteDTO.class);
    }

    private Paquete convertirAEntidad(PaqueteDTO dto){
        return modelMapper.map(dto, Paquete.class);
    }



}
