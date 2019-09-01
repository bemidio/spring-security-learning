package com.emidio.restapiauthtest.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * UsuarioController
 */
@RestController
@RequestMapping("/api")
public class UsuarioController {

   // @RequestMapping(value="/usuario", method = RequestMethod.GET)
   @GetMapping("/teste/{nome}")
   public String teste(@PathVariable("nome") String nome){
       return "Testado: " + nome;

   }


   @GetMapping("/auth/{nome}")
   public String autenticada(@PathVariable("nome") String nome){
       return "Autenticada: " + nome;

   }

    // public String getNome(@PathVariable("nome") String nome) {
    //     return "O nome é " + nome;
    // }
    
}