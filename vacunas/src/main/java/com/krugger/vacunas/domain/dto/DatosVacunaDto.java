package com.krugger.vacunas.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DatosVacunaDto {

    private Long codigo;
    private LocalDate fecha;
    private Integer ndosis;
    private Short tipo;

}
