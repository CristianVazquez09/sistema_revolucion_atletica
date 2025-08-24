package com.wolfpack.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wolfpack.model.Producto;
import com.wolfpack.model.Venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleVentaDTO {

    @EqualsAndHashCode.Include
    private Long idDetalleVenta;
    @JsonBackReference
    private VentaDTO venta;
    private ProductoDTO producto;
    private int cantidad;
    private BigDecimal subTotal;


}
