package com.wolfpack.repo;

import com.wolfpack.model.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISocioRepo extends IGenericRepo<Socio, Long>{
    @Query("""
        SELECT s
        FROM Socio s
        WHERE (:q IS NULL OR :q = '')
           OR LOWER(s.nombre)   LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(s.apellido) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    Page<Socio> buscarSociosPorNombre(@Param("q") String q, Pageable pageable);
}
