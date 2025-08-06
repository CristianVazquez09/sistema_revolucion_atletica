package com.wolfpack.controller;

import com.wolfpack.dto.VentaDTO;
import com.wolfpack.model.Venta;
import com.wolfpack.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/ventas")
@RequiredArgsConstructor
public class VentaController {
    //@Autowired
    private final IVentaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> buscarTodos() throws Exception{
        List<VentaDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        Venta obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody VentaDTO dto) throws Exception{
        Venta obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar( @PathVariable("id") Long id, @RequestBody VentaDTO dto) throws Exception{
        dto.setIdVenta(id);
        Venta obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private VentaDTO convertirADto(Venta obj){
        return modelMapper.map(obj, VentaDTO.class);
    }

    private Venta convertirAEntidad(VentaDTO dto){
        return modelMapper.map(dto, Venta.class);
    }



}
