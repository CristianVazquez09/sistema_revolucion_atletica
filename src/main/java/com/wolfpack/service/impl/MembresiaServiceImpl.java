package com.wolfpack.service.impl;

import com.wolfpack.model.Gimnasio;
import com.wolfpack.model.Membresia;
import com.wolfpack.model.Paquete;
import com.wolfpack.model.Socio;
import com.wolfpack.model.enums.TipoMovimiento;
import com.wolfpack.multitenancy.core.TenantContext;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IMembresiaRepo;
import com.wolfpack.repo.IPaqueteRepo;
import com.wolfpack.repo.ISocioRepo;
import com.wolfpack.service.IMembresiaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembresiaServiceImpl extends TenantCRUDImpl<Membresia, Long> implements IMembresiaService {

    private final IMembresiaRepo membresiaRepo;
    private final IPaqueteRepo paqueteRepo;
    private final ISocioRepo socioRepo;
    private final EntityManager entityManager;
    private final Clock clock;
    private final ZoneId zoneId;



    @Override
    protected IGenericRepo<Membresia, Long> getRepo() {
        return membresiaRepo;
    }

    @Override
    @Transactional
    public Membresia guardarMembresia(Membresia membresia) {
        Long tenantId = TenantContext.get();
        if (tenantId == null) {
            throw new IllegalStateException("No se pudo resolver el tenant actual");
        }
        if (membresia == null || membresia.getPaquete() == null || membresia.getPaquete().getIdPaquete() == null) {
            throw new IllegalArgumentException("Debe indicar un paquete válido");
        }
        if (membresia.getSocio() == null) {
            throw new IllegalArgumentException("Debe indicar los datos del socio");
        }
        if (membresia.getMovimiento() == null) {
            throw new IllegalArgumentException("Debe indicar el tipo de movimiento");
        }

        Gimnasio gymRef = entityManager.getReference(Gimnasio.class, tenantId);

        // 1) Paquete del tenant (si es de otro gym, findById no lo encontrará por el filtro)
        Paquete paquete = paqueteRepo.findById(membresia.getPaquete().getIdPaquete())
                .orElseThrow(() -> new EntityNotFoundException("Paquete no encontrado"));

        // 2) Resolver socio según movimiento
        Socio socio = resolverSocioEnMovimiento(membresia.getMovimiento(), membresia.getSocio(), gymRef);

        // 3) Calcular fechas (evitando solapamiento si hay membresía previa)
        LocalDate hoy = LocalDate.now(clock);
        LocalDate fechaInicio = calcularFechaInicio(membresia.getMovimiento(), socio, hoy);
        LocalDate fechaFin = fechaInicio.plusDays(obtenerDiasDelServicio(paquete));

        // 4) Calcular total (aplica costo de inscripción solo si INSCRIPCION)
        BigDecimal descuento = Optional.ofNullable(membresia.getDescuento()).orElse(BigDecimal.ZERO);
        BigDecimal total = calcularTotal(paquete, membresia.getMovimiento(), descuento);

        // 5) Armar y persistir (tenant-scoped)
        membresia.setGimnasio(gymRef);
        membresia.setSocio(socio);
        membresia.setPaquete(paquete);
        membresia.setFechaInicio(fechaInicio);
        membresia.setFechaFin(fechaFin);
        membresia.setTotal(total);

        return membresiaRepo.save(membresia);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Membresia> buscarMembresiasPorSocio(Long idSocio, Pageable pageable) {
        Socio socio = socioRepo.findById(idSocio)
                .orElseThrow(() -> new EntityNotFoundException("Socio no encontrado"));
        return membresiaRepo.findAllBySocio(socio, pageable);
    }

    @Override
    public List<Membresia> listarVigentesPorSocio(Long idSocio) {
        // Validar existencia del socio en el tenant actual (si es de otro tenant, el filtro lo oculta y aquí 404)
        Socio socio = socioRepo.findById(idSocio)
                .orElseThrow(() -> new EntityNotFoundException("Socio no encontrado"));

        LocalDate hoy = LocalDate.now(clock);

        // Versión “no expirada”
        return membresiaRepo.findVigentesPorSocio(socio.getIdSocio(), hoy);
    }

    // -------------------- Helpers de negocio --------------------

    private Socio resolverSocioEnMovimiento(TipoMovimiento movimiento, Socio socioPayload, Gimnasio gymRef) {
        if (movimiento == TipoMovimiento.INSCRIPCION) {
            // Permitir socio existente (si mandan id), o crear uno nuevo
            if (socioPayload.getIdSocio() != null) {
                return socioRepo.findById(socioPayload.getIdSocio())
                        .orElseThrow(() -> new EntityNotFoundException("Socio no encontrado"));
            }
            // Socio nuevo
            socioPayload.setGimnasio(gymRef);
            // Aquí puedes setear defaults (activo=true, normalizar teléfono, validar unicidad, etc.)
            return socioRepo.save(socioPayload);
        } else if (movimiento == TipoMovimiento.REINSCRIPCION) {
            // Reinscripción solo con socio existente
            if (socioPayload.getIdSocio() == null) {
                throw new IllegalArgumentException("Para REINSCRIPCION debe enviar idSocio");
            }
            return socioRepo.findById(socioPayload.getIdSocio())
                    .orElseThrow(() -> new EntityNotFoundException("Socio no encontrado"));
        } else {
            throw new IllegalArgumentException("Movimiento no soportado: " + movimiento);
        }
    }


    private LocalDate calcularFechaInicio(TipoMovimiento movimiento, Socio socio, LocalDate hoy){
        Optional<Membresia> ultimaOpt = membresiaRepo.findTopBySocioOrderByFechaFinDesc(socio);

        if (ultimaOpt.isEmpty()) {
            return hoy;
        }

        Membresia ultima = ultimaOpt.get();
        LocalDate candidato = ultima.getFechaFin().plusDays(1);

        if (movimiento == com.wolfpack.model.enums.TipoMovimiento.REINSCRIPCION) {
            long diasTranscurridos = ChronoUnit.DAYS.between(ultima.getFechaFin(), hoy);

            if (diasTranscurridos > 5) {
                // Han pasado más de 5 días desde que terminó: iniciar hoy, sin retrofechar
                return hoy;
            } else {
                // 0..5 días de “tolerancia” (o incluso si aún no termina), se enlaza desde el día siguiente
                return candidato;
            }
        }

        // Para INSCRIPCION (u otro): no retrofechar, mantener lógica “normal”
        return candidato.isAfter(hoy) ? candidato : hoy;
    }


    private int obtenerDiasDelServicio(Paquete paquete) {
        return paquete.getTiempo().getDias();
    }

    private BigDecimal calcularTotal(Paquete paquete, TipoMovimiento movimiento, BigDecimal descuento) {
        if (descuento.signum() < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        BigDecimal base = paquete.getPrecio();
        if (movimiento == TipoMovimiento.INSCRIPCION) {
            base = base.add(paquete.getCostoInscripcion());
        }
        BigDecimal total = base.subtract(descuento);
        if (total.signum() < 0) {
            total = BigDecimal.ZERO;
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
