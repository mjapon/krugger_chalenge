package com.krugger.vacunas.persistence.mapper;

import com.krugger.vacunas.domain.dto.DatosVacunaDto;
import com.krugger.vacunas.persistence.entity.DatosVacuna;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DatosVacunaMapper {

    @Mappings({
            @Mapping(source = "fechaVacunacion", target = "fecha"),
            @Mapping(source = "numdosis", target = "ndosis"),
    })
    DatosVacunaDto toDatosVacunaDto(DatosVacuna datosVacuna);

    @InheritInverseConfiguration
    @Mapping(target = "empleado", ignore = true)
    DatosVacuna toDatosVacuna(DatosVacunaDto datosVacunaDto);
}
