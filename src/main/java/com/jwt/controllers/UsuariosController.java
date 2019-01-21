package com.jwt.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.Cliente;

/**
 * Creado por  Ascari Q. Romo Pedraza - molder.itp@gmail.com on 28/04/17.
 */
@RestController
public class UsuariosController {

    @GetMapping(path = "/clientes")
    public List<Cliente> getUsers(){
        return Arrays.asList(new Cliente("Paco", "Gonzalez", "Rivera"), new Cliente("Pedro", "Lopez", "Zugaraz"), new Cliente("Juan", "Perz", "Jolote"));
    }

}
