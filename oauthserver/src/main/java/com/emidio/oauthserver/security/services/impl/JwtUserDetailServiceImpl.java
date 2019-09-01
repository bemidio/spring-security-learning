package com.emidio.oauthserver.security.services.impl;

import java.util.Optional;

import com.emidio.oauthserver.security.entidades.Usuario;
import com.emidio.oauthserver.security.services.UsuarioService;
import com.emidio.oauthserver.security.utils.JwtUserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Esta classe é usada de fato pelo Spring Security. É por ela que um usuario é de fato buscado. 
 * Eu uso o meu service para busar o usuario e converto em um UserDetails.
 * JwtUser vai implementar UserDetails.
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(username);

        if(usuario.isPresent()){
            return JwtUserFactory.create(usuario.get());
        }

        throw new UsernameNotFoundException("Usuario não encontrado.");
	}    
}