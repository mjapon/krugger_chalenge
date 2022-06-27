package com.krugger.vacunas.persistence.crud;

import com.krugger.vacunas.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
