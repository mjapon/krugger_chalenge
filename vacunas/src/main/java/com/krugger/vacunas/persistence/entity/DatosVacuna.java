package com.krugger.vacunas.persistence.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "datos_vacunas")
public class DatosVacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false)
    private Short tipo;

    @Column(nullable = false)
    private LocalDate fechaVacunacion;

    @Column(nullable = false)
    private Short numdosis;

    @OneToOne(mappedBy = "datosVacuna")
    private Empleado empleado;
}
