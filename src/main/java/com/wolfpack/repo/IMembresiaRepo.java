package com.wolfpack.repo;

import com.wolfpack.model.Membresia;
import com.wolfpack.model.Socio;

public interface IMembresiaRepo extends IGenericRepo<Membresia, Long>{

    boolean existsAllBySocio(Socio socio);
}

