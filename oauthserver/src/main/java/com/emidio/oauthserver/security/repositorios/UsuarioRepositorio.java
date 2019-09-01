package com.emidio.oauthserver.security.repositorios;


import com.emidio.oauthserver.security.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * UsuarioRepositorio
 */
@Transactional(readOnly = true)
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);
    
}