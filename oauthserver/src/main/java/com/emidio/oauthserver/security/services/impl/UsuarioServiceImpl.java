package com.emidio.oauthserver.security.services.impl;

import java.util.Optional;

import com.emidio.oauthserver.security.entidades.Usuario;
import com.emidio.oauthserver.security.repositorios.UsuarioRepositorio;
import com.emidio.oauthserver.security.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * UsuarioServiceImpl
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositorio repositorio;
    
    @Override
	public Optional<Usuario> buscarPorEmail(String email) {
        
        return Optional.ofNullable(this.repositorio.findByEmail(email));
	}    
}