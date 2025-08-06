package com.wolfpack.controller;

import com.wolfpack.dto.ProductoDTO;
import com.wolfpack.model.Producto;
import com.wolfpack.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/productos")
@RequiredArgsConstructor
public class ProductoController {
    //@Autowired
    private final IProductoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> buscarTodos() throws Exception{
        List<ProductoDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable("id") Long id) throws Exception {
        Producto obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody ProductoDTO dto) throws Exception{
        Producto obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar( @PathVariable("id") Long id, @RequestBody ProductoDTO dto) throws Exception{
        dto.setIdProducto(id);
        Producto obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private ProductoDTO convertirADto(Producto obj){
        return modelMapper.map(obj, ProductoDTO.class);
    }

    private Producto convertirAEntidad(ProductoDTO dto){
        return modelMapper.map(dto, Producto.class);
    }



}
