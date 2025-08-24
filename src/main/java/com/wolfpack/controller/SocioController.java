package com.wolfpack.controller;

import com.wolfpack.dto.SocioDTO;
import com.wolfpack.model.Socio;
import com.wolfpack.service.ISocioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/socios")
@RequiredArgsConstructor
public class SocioController {
    //@Autowired
    private final ISocioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SocioDTO>> buscarTodos() throws Exception{
        List<SocioDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<SocioDTO>> buscarSocios(Pageable pageable) {
        Page<SocioDTO> list = service.buscarSocios(pageable).map(this::convertirADto);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<Page<SocioDTO>> buscarSociosPorNombre(@PathVariable("nombre") String nombre, Pageable pageable) {


        Page<SocioDTO> lista = service.buscarSociosPorNombre(nombre, pageable).map(this::convertirADto);
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        Socio obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody SocioDTO dto) throws Exception{
        Socio obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSocio()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioDTO> actualizar( @PathVariable("id") Long id, @RequestBody SocioDTO dto) throws Exception{
        dto.setIdSocio(id);
        Socio obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private SocioDTO convertirADto(Socio obj){
        return modelMapper.map(obj, SocioDTO.class);
    }

    private Socio convertirAEntidad(SocioDTO dto){
        return modelMapper.map(dto, Socio.class);
    }



}
