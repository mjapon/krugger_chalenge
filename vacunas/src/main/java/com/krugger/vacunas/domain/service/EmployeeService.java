package com.krugger.vacunas.domain.service;

import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.persistence.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UserService userService;


    public List<EmpleadoDto> getAll() {
        return empleadoRepository.getAll();
    }

    public List<EmpleadoDto> getAllByIsVacunado(Boolean vacunado) {
        return empleadoRepository.findAllByVacunado(vacunado);
    }

    public List<EmpleadoDto> getAllByTipoVacuna(Short tipoVacuna) {
        return empleadoRepository.findAllByTipoVacuna(tipoVacuna);
    }

    public List<EmpleadoDto> getAllVacunadosBetweenDates(LocalDate desde, LocalDate hasta) {
        return empleadoRepository.findAllByRangoFechaVacuna(desde, hasta);
    }

    public Optional<EmpleadoDto> getEmpleado(Long empId) {
        return empleadoRepository.getEmpleado(empId);
    }

    @Transactional
    public EmpleadoDto update(EmpleadoDto currentValue, EmpleadoDto newValue) {

        currentValue.setNombres(newValue.getNombres());
        currentValue.setApellidos(newValue.getApellidos());
        currentValue.setEmail(newValue.getEmail());
        currentValue.setCedula(newValue.getCedula());

        currentValue.setFechaNac(newValue.getFechaNac());
        currentValue.setDirDomicilio(newValue.getDirDomicilio());
        currentValue.setCelular(newValue.getCelular());
        currentValue.setVacunado(newValue.getVacunado());
        if (newValue.getVacunado() != null && newValue.getVacunado()) {
            currentValue.setDatosVacuna(newValue.getDatosVacuna());
        } else {
            currentValue.setDatosVacuna(null);
        }
        return save(currentValue);
    }

    @Transactional
    public EmpleadoDto save(EmpleadoDto empleadoDto) {
        userService.createUser(empleadoDto);
        return empleadoRepository.save(empleadoDto);
    }

    @Transactional
    public boolean delete(Long empId) {
        return getEmpleado(empId).map(empleado -> {
            empleadoRepository.delete(empId);
            return true;
        }).orElse(false);
    }

}
