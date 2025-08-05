package com.wolfpack.model.enums;

import lombok.Getter;

@Getter
public enum TiempoPlan {
    VISITA(1, "Visita", 1),           // 1 día
    DIEZ_DIAS(2, "10 Días", 10),
    QUINCE_DIAS(3, "15 Días", 15),
    UNA_SEMANA(4, "1 Semana", 7),
    DOS_SEMANAS(5, "2 Semanas", 14),
    UN_MES(6, "1 Mes", 30),
    TRES_MESES(7, "3 Meses", 90),
    SEIS_MESES(8, "6 Meses", 180),
    UN_ANIO(9, "1 Año", 365);

    private final int id;
    private final String descripcion;
    private final int dias;

    TiempoPlan(int id, String descripcion, int dias) {
        this.id = id;
        this.descripcion = descripcion;
        this.dias = dias;
    }

}
