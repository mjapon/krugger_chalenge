package com.krugger.vacunas.persistence.mapper;

import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.persistence.entity.Empleado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring", uses = {DatosVacunaMapper.class})
public interface EmpleadoMapper {

    @Mappings({
            @Mapping(source = "codigo", target = "empId"),
            @Mapping(source = "correoElectronico", target = "email"),
            @Mapping(source = "fechaNacimiento", target = "fechaNac")
    })
    EmpleadoDto toEmpleadoDto(Empleado empleado);

    List<EmpleadoDto> toEmpleadosDto(List<Empleado> empleados);

    @InheritInverseConfiguration
    Empleado toEmpleado(EmpleadoDto empleadoDto);

}
