package com.emidio.oauthserver.security.services;

import java.util.Optional;

import com.emidio.oauthserver.security.entidades.Usuario;

/**
 * UsuarioService
 */
public interface UsuarioService{

    Optional<Usuario> buscarPorEmail(String email);
    
}