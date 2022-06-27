package com.krugger.vacunas.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, unique = true, length = 10)
    private String cedula;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "dir_domicilio")
    private String dirDomicilio;

    private String celular;

    private Boolean vacunado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vacuna", referencedColumnName = "codigo")
    private DatosVacuna datosVacuna;

}
