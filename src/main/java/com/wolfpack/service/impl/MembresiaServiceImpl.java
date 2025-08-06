package com.wolfpack.service.impl;

import com.wolfpack.model.*;
import com.wolfpack.repo.*;
import com.wolfpack.service.IMembresiaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MembresiaServiceImpl extends CRUDImpl<Membresia, Long> implements IMembresiaService {

    private final IMembresiaRepo membresiaRepo;
    private  final IPaqueteRepo paqueteRepo;
    @Override
    protected IGenericRepo<Membresia, Long> getRepo() {
        return membresiaRepo;
    }

    @Override
    @Transactional
    public Membresia guardarMembresia(Membresia membresia) {

        Paquete paquete = paqueteRepo.findById(membresia.getPaquete().getIdPaquete())
                .orElseThrow( () -> new EntityNotFoundException("Paquete no encontrado"));
        BigDecimal total= obtenerCostoTotal(paquete);


        int dias = obtenerDiasDelServicio(paquete);
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFin = fechaActual.plusDays(dias);


        membresia.setTotal(total.subtract(membresia.getDescuento()));
        membresia.setFechaInicio(fechaActual);
        membresia.setFechaFin(fechaFin);

        return membresiaRepo.save(membresia);
    }


    private int obtenerDiasDelServicio(Paquete paquete){
        return paquete.getTiempo().getDias();
    }

    private BigDecimal obtenerCostoTotal(Paquete paquete){
        BigDecimal costoInscripcion = BigDecimal.ZERO;
        BigDecimal costoServicio;

        costoInscripcion = paquete.getCostoInscripcion();

        costoServicio = paquete.getPrecio();

        return costoServicio.add(costoInscripcion);
    }





}
