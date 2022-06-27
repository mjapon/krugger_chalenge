package com.krugger.vacunas.persistence.crud;


import com.krugger.vacunas.persistence.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmpleadoCrudRepository extends CrudRepository<Empleado, Long> {
    Optional<Empleado> findBycorreoElectronico(String correoElectronico);

    List<Empleado> findAllByvacunado(Boolean vacunado);

    List<Empleado> findAllByDatosVacunaTipo(Short tipo);

    List<Empleado> findAllByDatosVacunaFechaVacunacionBetween(LocalDate to, LocalDate from);

}
