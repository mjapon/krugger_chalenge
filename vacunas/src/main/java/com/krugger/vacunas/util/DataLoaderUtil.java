package com.krugger.vacunas.util;

import com.krugger.vacunas.domain.dto.DatosVacunaDto;
import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.domain.service.EmployeeService;
import com.krugger.vacunas.persistence.crud.EmpleadoCrudRepository;
import com.krugger.vacunas.persistence.crud.UsuarioCrudRepository;
import com.krugger.vacunas.persistence.entity.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;


@Component()
public class DataLoaderUtil implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(DataLoaderUtil.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        createTestEmployees();
    }

    public void createTestEmployees() {
        try {
            String adminEmail = "admin@domain.com";

            if (!empleadoCrudRepository.findBycorreoElectronico(adminEmail).isPresent()) {
                EmpleadoDto adminEmpDto = new EmpleadoDto();
                adminEmpDto.setCedula("1703744068");
                adminEmpDto.setNombres("Jhon");
                adminEmpDto.setApellidos("Doe");
                adminEmpDto.setEmail(adminEmail);
                DatosVacunaDto datosVacuna = new DatosVacunaDto();
                datosVacuna.setFecha(LocalDate.now());
                datosVacuna.setTipo((short) 1);
                datosVacuna.setNdosis(2);
                adminEmpDto.setVacunado(Boolean.TRUE);
                employeeService.save(adminEmpDto);

                Optional<Usuario> usuario = usuarioCrudRepository.findByCorreo(adminEmail);
                if (usuario.isPresent()) {
                    Usuario user = usuario.get();
                    user.setRol("admin");
                    usuarioCrudRepository.save(user);
                }
            }

            String empEmail = "empleado@domain.com";

            if (!empleadoCrudRepository.findBycorreoElectronico(empEmail).isPresent()) {
                EmpleadoDto empleadoDto = new EmpleadoDto();

                empleadoDto.setCedula("0201822981");
                empleadoDto.setNombres("Juan");
                empleadoDto.setApellidos("Perez");
                empleadoDto.setEmail(empEmail);
                DatosVacunaDto datosVacuna = new DatosVacunaDto();
                datosVacuna.setFecha(LocalDate.now());
                datosVacuna.setTipo((short) 2);
                datosVacuna.setNdosis(1);
                empleadoDto.setVacunado(Boolean.TRUE);
                empleadoDto.setDatosVacuna(datosVacuna);

                employeeService.save(empleadoDto);
            }

        } catch (Exception e) {
            logger.info("Error al cargar datos de prueba", e);
        }

    }
}
