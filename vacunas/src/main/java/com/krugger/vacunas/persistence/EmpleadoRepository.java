package com.krugger.vacunas.persistence;

import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.domain.repository.EmpleyeeRepository;
import com.krugger.vacunas.persistence.crud.EmpleadoCrudRepository;
import com.krugger.vacunas.persistence.entity.Empleado;
import com.krugger.vacunas.persistence.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepository implements EmpleyeeRepository {

    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;

    @Autowired
    private EmpleadoMapper mapper;

    @Override
    public List<EmpleadoDto> getAll() {
        List<Empleado> empleados = (List<Empleado>) empleadoCrudRepository.findAll();
        return mapper.toEmpleadosDto(empleados);
    }

    @Override
    public Optional<EmpleadoDto> getEmpleado(Long empId) {
        return empleadoCrudRepository.findById(empId).map(empleado -> mapper.toEmpleadoDto(empleado));
    }

    @Override
    public List<EmpleadoDto> findAllByVacunado(Boolean vacunado) {
        List<Empleado> empleados = empleadoCrudRepository.findAllByvacunado(vacunado);
        return mapper.toEmpleadosDto(empleados);
    }

    @Override
    public List<EmpleadoDto> findAllByTipoVacuna(Short tipo) {
        List<Empleado> empleados = empleadoCrudRepository.findAllByDatosVacunaTipo(tipo);
        return mapper.toEmpleadosDto(empleados);
    }

    @Override
    public List<EmpleadoDto> findAllByRangoFechaVacuna(LocalDate desde, LocalDate hasta) {
        List<Empleado> empleados = empleadoCrudRepository.findAllByDatosVacunaFechaVacunacionBetween(desde, hasta);
        return mapper.toEmpleadosDto(empleados);
    }

    @Override
    public EmpleadoDto save(EmpleadoDto empleadoDto) {
        Empleado empleado = mapper.toEmpleado(empleadoDto);
        return mapper.toEmpleadoDto(empleadoCrudRepository.save(empleado));
    }

    @Override
    public void delete(Long empId) {
        empleadoCrudRepository.deleteById(empId);
    }
}
