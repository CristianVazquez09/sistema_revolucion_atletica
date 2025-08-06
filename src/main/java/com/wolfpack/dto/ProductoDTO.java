package com.wolfpack.dto;

import com.wolfpack.model.Categoria;
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
public class ProductoDTO {

    @EqualsAndHashCode.Include
    private Long idProducto;
    private String nombre;
    private String codigo;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private int cantidad;
    private CategoriaDTO categoria;


}
