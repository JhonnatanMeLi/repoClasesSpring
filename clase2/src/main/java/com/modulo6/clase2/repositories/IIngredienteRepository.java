package com.modulo6.clase2.repositories;

import com.modulo6.clase2.dtos.IngredienteRespDto;

import java.util.List;

public interface IIngredienteRepository {

    IngredienteRespDto consultarIngrediente(String nombre);

}
