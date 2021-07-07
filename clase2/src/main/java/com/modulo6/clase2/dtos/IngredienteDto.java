package com.modulo6.clase2.dtos;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class IngredienteDto {

    @NotNull(message = "El nombre del ingrediente es obligatorio")
    @NotBlank(message = "El nombre del ingrediente es invalido")
    private String nombre;

    @NotNull(message = "El peso en gr del ingrediente es obligatorio")
    @DecimalMin(message = "El peso del ingrediente no puede ser negativo", value = "0")
    private Double peso;


}
