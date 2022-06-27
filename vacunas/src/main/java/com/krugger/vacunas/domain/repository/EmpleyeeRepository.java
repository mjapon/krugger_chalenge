package com.krugger.vacunas.domain.repository;

import com.krugger.vacunas.domain.dto.EmpleadoDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmpleyeeRepository {

    List<EmpleadoDto> getAll();

    Optional<EmpleadoDto> getEmpleado(Long empId);

    List<EmpleadoDto> findAllByVacunado(Boolean vacunado);

    List<EmpleadoDto> findAllByTipoVacuna(Short tipo);

    List<EmpleadoDto> findAllByRangoFechaVacuna(LocalDate desde, LocalDate hasta);

    EmpleadoDto save(EmpleadoDto empleadoDto);

    void delete(Long empId);

}
