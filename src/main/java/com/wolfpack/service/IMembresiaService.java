package com.wolfpack.service;

import com.wolfpack.model.Membresia;


public interface IMembresiaService extends ICRUD<Membresia, Long>{

    Membresia guardarMembresia(Membresia membresia);

}
