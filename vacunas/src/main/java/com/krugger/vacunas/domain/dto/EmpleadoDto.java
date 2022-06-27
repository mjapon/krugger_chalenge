package com.krugger.vacunas.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class EmpleadoDto {

    private Long empId;

    @NotNull(message = "El número de cedula es requerido")
    @Size(min = 10, max = 10)
    private String cedula;

    @NotNull(message = "Los nombres son requeridos")
    private String nombres;

    @NotNull(message = "Los apellidos son requeridos")
    private String apellidos;

    @NotNull(message = "La direccion de correo es requerida")
    @Email(message = "La dirección de correo es invalida")
    private String email;

    @Past(message = "Verifique la fehca de nacimiento enviada.")
    private LocalDate fechaNac;

    private String dirDomicilio;

    private String celular;

    private Boolean vacunado;

    private DatosVacunaDto datosVacuna;

}
