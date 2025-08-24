package com.wolfpack.controller;

import com.wolfpack.dto.CategoriaDTO;
import com.wolfpack.model.Categoria;
import com.wolfpack.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    //@Autowired
    private final ICategoriaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodos() throws Exception{
        List<CategoriaDTO> list = service.buscarTodos().stream().map(this::convertirADto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable("id") Integer id) throws Exception {
        Categoria obj = service.buscarPorId(id);

        return ResponseEntity.ok(convertirADto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@RequestBody CategoriaDTO dto) throws Exception{
        Categoria obj = service.guardar(convertirAEntidad(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCategoria()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar( @PathVariable("id") Integer id, @RequestBody CategoriaDTO dto) throws Exception{
        dto.setIdCategoria(id);
        Categoria obj = service.actualizar(id, convertirAEntidad(dto));

        return ResponseEntity.ok(convertirADto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    private CategoriaDTO convertirADto(Categoria obj){
        return modelMapper.map(obj, CategoriaDTO.class);
    }

    private Categoria convertirAEntidad(CategoriaDTO dto){
        return modelMapper.map(dto, Categoria.class);
    }



}
