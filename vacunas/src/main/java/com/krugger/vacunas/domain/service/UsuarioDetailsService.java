package com.krugger.vacunas.domain.service;

import com.krugger.vacunas.persistence.crud.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioCrudRepository.findByCorreo(email)
                .map(usuario ->
                        new User(
                                usuario.getCorreo(),
                                usuario.getClave(),
                                usuario.getActivo(),
                                true,
                                true,
                                true,
                                getAuthorities(usuario.getRol())
                        )
                ).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String rol) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rol));
        return authorities;
    }
}
