package com.modulo6.clase2.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PlatoDto {

    @NotNull(message = "El nombre del plato es obligatorio")
    @NotBlank(message = "El nombre del plato es invalido")
    private String nombre;

    @NotEmpty(message = "El plato debe tener almenos un ingrediente")
    @Valid
    private List<IngredienteDto> ingredientes;

}
