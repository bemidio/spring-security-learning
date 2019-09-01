package com.emidio.oauthserver.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExemploController
 */
@RestController
@RequestMapping("/api/exemplo")
public class ExemploController {

    @GetMapping(value = "/{nome}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String exemplo(@PathVariable("nome") String nome){

        return "Olá " + nome;
    }
    
}       