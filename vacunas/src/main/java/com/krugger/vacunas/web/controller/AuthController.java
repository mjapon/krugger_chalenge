package com.krugger.vacunas.web.controller;

import com.krugger.vacunas.domain.dto.AuthRequestDto;
import com.krugger.vacunas.domain.dto.AuthResponseDto;
import com.krugger.vacunas.domain.service.UsuarioDetailsService;
import com.krugger.vacunas.persistence.crud.EmpleadoCrudRepository;
import com.krugger.vacunas.persistence.entity.Empleado;
import com.krugger.vacunas.web.security.JWTUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/aut")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    @ApiOperation("Autentica un usuario dado el correo y la clave y retorna un token en el caso de login exitoso")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Autenticación exitosa"),
            @ApiResponse(code = 403, message = "Si las credenciales enviadas son erroneas")
    })
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            Long empid = 0L;
            Optional<Empleado> empleado = empleadoCrudRepository.findBycorreoElectronico(userDetails.getUsername());
            if (empleado.isPresent()) {
                empid = empleado.get().getCodigo();
            }

            return new ResponseEntity<>(new AuthResponseDto(empid, userDetails.getUsername(), jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
