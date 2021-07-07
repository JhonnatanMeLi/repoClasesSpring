package com.modulo6.clase2.controllers;

import com.modulo6.clase2.dtos.PriceDto;
import com.modulo6.clase2.dtos.request.CasaDto;
import com.modulo6.clase2.dtos.response.CasaResponseDto;
import com.modulo6.clase2.exceptions.ErrorMessage;
import com.modulo6.clase2.services.implementation.CalcularAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class CasaController {

    @Autowired
    CalcularAreaService calcularAreaService;

    @PostMapping("/calcular-area")
    public ResponseEntity<CasaResponseDto> calcularArea(@Valid @RequestBody CasaDto casa) {
        try {
            return ResponseEntity.ok(calcularAreaService.calcularArea(casa));
        } catch (ErrorMessage e) {
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{location}")
    public PriceDto probarMapper(@PathVariable String location) {
        return calcularAreaService.probarMapper(location);
    }

}
