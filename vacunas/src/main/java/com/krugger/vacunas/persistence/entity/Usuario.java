package com.krugger.vacunas.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false, length = 60)
    private String clave;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private String rol;


}
