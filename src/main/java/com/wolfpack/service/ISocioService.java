package com.wolfpack.service;

import com.wolfpack.dto.SocioDTO;
import com.wolfpack.model.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ISocioService extends ICRUD<Socio, Long>{

    Page<Socio> buscarSocios(Pageable pageable);

    Page<Socio> buscarSociosPorNombre(String nombre, Pageable pageable);

}
