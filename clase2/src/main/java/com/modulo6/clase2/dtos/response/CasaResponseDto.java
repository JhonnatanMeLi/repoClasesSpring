package com.modulo6.clase2.dtos.response;

import com.modulo6.clase2.dtos.request.HabitacionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CasaResponseDto {

    private Double areaTotal;
    private Double valorCasa;
    private HabitacionDto habitacionGrande;
    private List<HabitacionDto> habitaciones;

}
