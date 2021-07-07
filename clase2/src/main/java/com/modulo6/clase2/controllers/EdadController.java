package com.modulo6.clase2.controllers;

import com.modulo6.clase2.dtos.response.EdadDto;
import com.modulo6.clase2.services.implementation.CalcularEdad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edad")
public class EdadController {

    @Autowired
    CalcularEdad calcularEdad;

    @GetMapping("/{dia}/{mes}/{anio}")
    public ResponseEntity<EdadDto> calcularEdad(@PathVariable(value = "dia") Integer dia,
                                                @PathVariable(value = "mes") Integer mes,
                                                @PathVariable(value = "anio") Integer anio) {

        return ResponseEntity.ok(calcularEdad.calcularEdad(dia, mes, anio));
    }

}
