package com.wolfpack.controller;

import com.wolfpack.dto.membresia.MembresiaRequestDTO;
import com.wolfpack.dto.membresia.MembresiaResponseDTO;
import com.wolfpack.model.Membresia;
import com.wolfpack.service.IMembresiaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<MembresiaResponseDTO>> buscarTodos() throws Exception{
        List<MembresiaResponseDTO> list = service.buscarTodos().stream().map(this::convertirADtoResponse).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaResponseDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        Membresia obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADtoResponse(obj));
    }

    @GetMapping("buscar/socio/{idSocio}")
    public ResponseEntity<Page<MembresiaResponseDTO>> buscarPorIdSocio(@PathVariable("idSocio") Long id, Pageable pageable)  {
        Page<MembresiaResponseDTO> obj = service.buscarMembresiasPorSocio(id, pageable).map(this::convertirADtoResponse);

        return ResponseEntity.ok(obj);
    }

    @GetMapping("/por-socio/{idSocio}/vigentes")
    public ResponseEntity<List<MembresiaResponseDTO>> listarVigentesPorSocio(@PathVariable Long idSocio) {
        List<MembresiaResponseDTO> list = service.listarVigentesPorSocio(idSocio)
                .stream()
                .map(this::convertirADtoResponse)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody MembresiaRequestDTO dto) throws Exception{
        Membresia obj = service.guardarMembresia(convertirAEntidadRequest(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMembresia()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/reinscripcion")
    public ResponseEntity<Void> reinscripcion(@RequestBody MembresiaRequestDTO dto) {
        Membresia obj = service.guardarMembresia(convertirAEntidadRequest(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMembresia()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaResponseDTO> actualizar(@PathVariable("id") Long id, @RequestBody MembresiaRequestDTO dto) throws Exception{
        dto.setIdMembresia(id);
        Membresia obj = service.actualizar(id, convertirAEntidadRequest(dto));

        return ResponseEntity.ok(convertirADtoResponse(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Membresia convertirAEntidadRequest(MembresiaRequestDTO dto){
        return modelMapper.map(dto, Membresia.class);
    }

    private MembresiaResponseDTO convertirADtoResponse(Membresia obj){
        return modelMapper.map(obj, MembresiaResponseDTO.class);
    }



}
