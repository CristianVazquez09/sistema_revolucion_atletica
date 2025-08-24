package com.wolfpack.controller;

import com.wolfpack.dto.AccesoriaPersonalizadaDTO;
import com.wolfpack.model.AccesoriaPersonalizada;
import com.wolfpack.service.IAccesoriaPersonalizadaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/accesorias")
@RequiredArgsConstructor
public class AccesoriaPersonalizadaController {
    //@Autowired
    private final IAccesoriaPersonalizadaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AccesoriaPersonalizadaDTO>> buscarTodos() throws Exception{
        List<AccesoriaPersonalizadaDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesoriaPersonalizadaDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        AccesoriaPersonalizada obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody AccesoriaPersonalizadaDTO dto) throws Exception{
        AccesoriaPersonalizada obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAccesoriaPersonalizada()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccesoriaPersonalizadaDTO> actualizar( @PathVariable("id") Long id, @RequestBody AccesoriaPersonalizadaDTO dto) throws Exception{
        dto.setIdAccesoriaPersonalizada(id);
        AccesoriaPersonalizada obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private AccesoriaPersonalizadaDTO convertirADto(AccesoriaPersonalizada obj){
        return modelMapper.map(obj, AccesoriaPersonalizadaDTO.class);
    }

    private AccesoriaPersonalizada convertirAEntidad(AccesoriaPersonalizadaDTO dto){
        return modelMapper.map(dto, AccesoriaPersonalizada.class);
    }



}
