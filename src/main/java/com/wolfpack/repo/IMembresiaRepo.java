package com.wolfpack.repo;

import com.wolfpack.model.Membresia;
import com.wolfpack.model.Socio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMembresiaRepo extends IGenericRepo<Membresia, Long>{

    boolean existsAllBySocio(Socio socio);

    Page<Membresia> findAllBySocio(Socio socio, Pageable pageable);


    Optional<Membresia> findTopBySocioOrderByFechaFinDesc(Socio socio);

    @Query("""
        SELECT DISTINCT m
        FROM Membresia m
        JOIN FETCH m.socio s
        JOIN FETCH m.paquete p
        WHERE s.idSocio = :idSocio
          AND m.fechaFin > :hoy
        ORDER BY m.fechaFin ASC
    """)
    List<Membresia> findVigentesPorSocio(@Param("idSocio") Long idSocio,
                                         @Param("hoy") LocalDate hoy);
}

