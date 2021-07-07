package com.modulo6.clase2.controllers;

import com.modulo6.clase2.dtos.PlatoDto;
import com.modulo6.clase2.dtos.RespuestaDto;
import com.modulo6.clase2.services.ICaloriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    ICaloriasService calcularCaloriasService;

    @GetMapping("/plato")
    public ResponseEntity<RespuestaDto> calcularPlato(@Valid @RequestBody PlatoDto plato) {
        return ResponseEntity.ok(calcularCaloriasService.calcularCalorias(plato));
    }


    @PostMapping("/platos")
    public ResponseEntity<List<RespuestaDto>> calcularPlato(@Valid @RequestBody List<PlatoDto> platos) {
        return ResponseEntity.ok(calcularCaloriasService.procesarPlatos(platos));
    }

}
