package com.modulo6.clase2.services;

import com.modulo6.clase2.dtos.PlatoDto;
import com.modulo6.clase2.dtos.RespuestaDto;

import java.util.List;

public interface ICaloriasService {

    RespuestaDto calcularCalorias(PlatoDto plato);

    List<RespuestaDto> procesarPlatos(List<PlatoDto> platos);

}
