package com.modulo6.clase2.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaDto {

    private String ingredienteMasCalorias;
    private Double caloriasPlato;
    private List<IngredienteRespDto> ingredientes;

}
