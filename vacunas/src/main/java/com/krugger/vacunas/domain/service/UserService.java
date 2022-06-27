package com.krugger.vacunas.domain.service;


import com.krugger.vacunas.domain.dto.EmpleadoDto;
import com.krugger.vacunas.persistence.crud.UsuarioCrudRepository;
import com.krugger.vacunas.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final String DEFAULT_PASSWORD = "$2a$11$mfNb8hLb8lFRRLDwxQnQdOxSdLqJfQCnSplQKWRlQHA2OV9Fp.4aa";
    private final String DEFAULT_ROL = "empleado";

    @Autowired
    private UsuarioCrudRepository userCrudRepository;

    public Usuario createUser(EmpleadoDto empleadoDto) {
        Usuario usuario = new Usuario();
        usuario.setActivo(Boolean.TRUE);
        usuario.setClave(DEFAULT_PASSWORD);
        usuario.setRol(DEFAULT_ROL);
        usuario.setCorreo(empleadoDto.getEmail());
        userCrudRepository.save(usuario);

        return usuario;
    }

}
