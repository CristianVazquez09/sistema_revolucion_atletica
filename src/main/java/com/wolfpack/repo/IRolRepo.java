package com.wolfpack.repo;

import com.wolfpack.model.Rol;
import com.wolfpack.model.Rol;

public interface IRolRepo extends IGenericRepo<Rol, Integer>{
    
    Rol findByNombre(String nombreRol);
}
