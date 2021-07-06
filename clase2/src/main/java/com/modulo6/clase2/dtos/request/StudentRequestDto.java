package com.modulo6.clase2.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class StudentRequestDto {

    @Max(message = "Menor a 100", value = 100)
    @Min(message = "Mayor a 10", value = 10)
    private long id;

    @NotNull(message = "El dni es null")
    private String dni;

    @NotBlank(message = "El nombre esta vacio")
    @NotNull(message = "El nombre es null")
    private String name;

    @NotEmpty(message = "Esta colección esta vacia")
    private List<String> apellidos;

}
