package com.emidio.oauthserver.security.utils;

import java.util.ArrayList;
import java.util.List;

import com.emidio.oauthserver.security.entidades.Usuario;
import com.emidio.oauthserver.security.enums.PerfilEnum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * JwtUserFactory
 */
public class JwtUserFactory {

    public static JwtUser create(Usuario usuario) {

        return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
                mapToGrantedAuthorities(usuario.getPerfil()));
    }

    /**
     * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
     * 
     * @param perfilEnum
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }
}