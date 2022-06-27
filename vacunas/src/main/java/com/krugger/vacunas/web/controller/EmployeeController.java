package com.krugger.vacunas.web.controller;

import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.domain.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employes")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    @ApiOperation("Obtiene todos los empleados")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<EmpleadoDto>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getByVacunado/{vacunado}")
    @ApiOperation("Obtiene todos los empleados que esten o no vacunados segun el parametros enviado")
    public ResponseEntity<List<EmpleadoDto>> getAllByVacunado(
            @ApiParam(value = "Indica la condicacion de vacunado (1:Si, 0:No)", required = true, example = "1 o 0")
            @PathVariable("vacunado") Integer vacunado) {
        return new ResponseEntity<>(employeeService.getAllByIsVacunado(vacunado == 1), HttpStatus.OK);

    }

    @GetMapping("/getByTipoVacuna/{tipo}")
    @ApiOperation("Obtiene todos los empleados vacunados segun el tipo de vacuna enviado")
    public ResponseEntity<List<EmpleadoDto>> getAllByVacunado(
            @ApiParam(value = "Indica el tipo de vacuna", required = true, example = "1,2 o 3")
            @PathVariable("tipo") Short tipo) {
        return new ResponseEntity<>(employeeService.getAllByTipoVacuna(tipo), HttpStatus.OK);

    }

    @GetMapping("/getByRangoFechaVacuna/{desde}/{hasta}")
    @ApiOperation("Obtiene todos los empleados vacunados en un rango de fechas")
    public ResponseEntity<List<EmpleadoDto>> getAllByRangoFechaVacuna(
            @PathVariable("desde") String desde,
            @PathVariable("hasta") String hasta
    ) {

        return new ResponseEntity<>(employeeService.getAllVacunadosBetweenDates(LocalDate.parse(desde),
                LocalDate.parse(hasta)), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ApiOperation("Busca un empleado por su código")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Empleado no encontrado")
    })
    public ResponseEntity<EmpleadoDto> getEmployee(
            @ApiParam(value = "El código del empleado", required = true, example = "7")
            @PathVariable("id") Long empId) {
        return employeeService.getEmpleado(empId)
                .map(empleadoDto -> new ResponseEntity<>(empleadoDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Crea  un nuevo empleado con la información enviada")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Nuevo empleado registrado"),
            @ApiResponse(code = 400, message = "Cuado se genera algun error de validación o de base de datos")
    })
    public ResponseEntity<EmpleadoDto> save(@Valid @RequestBody EmpleadoDto empleado) {
        return new ResponseEntity<>(employeeService.save(empleado), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Actualiza la información de un empleado " +
            "La informacion que se puede actualizar es: fecha de nacimiento, direcccion de domicilio, " +
            "telefono movil, estado de vacunacion(tipo, fecha, numdosis)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Actualización exitosa"),
            @ApiResponse(code = 404, message = "Empleado no encontrado"),
            @ApiResponse(code = 400, message = "Cuado se genera algun error de validación o de base de datos")
    })
    public ResponseEntity<EmpleadoDto> update(
            @ApiParam(value = "El código del empleado que se desea actualizar", required = true, example = "7")
            @PathVariable("id") Long empId,
            @ApiParam(value = "La información del empleado que se desea actualizar")
            @RequestBody EmpleadoDto empleado) {

        return employeeService.getEmpleado(empId).map(empleadoDto ->
                new ResponseEntity<>(employeeService.update(empleadoDto, empleado), HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Elimina un empleado de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Eliminado exitoso"),
            @ApiResponse(code = 404, message = "Empleado no encontrado")
    })
    public ResponseEntity delete(
            @ApiParam(value = "El código del empleado que se desea eliminar", required = true, example = "7")
            @PathVariable("id") Long idEmp) {
        if (employeeService.delete(idEmp)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
