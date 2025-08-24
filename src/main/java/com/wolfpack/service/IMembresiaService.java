package com.wolfpack.service;

import com.wolfpack.model.Membresia;
import com.wolfpack.model.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IMembresiaService extends ICRUD<Membresia, Long>{

    Membresia guardarMembresia(Membresia membresia);

    Page<Membresia> buscarMembresiasPorSocio(Long idSocio, Pageable pageable);

    List<Membresia> listarVigentesPorSocio(Long idSocio);

    //Membresia reinscripcion(Membresia membresia);

}
