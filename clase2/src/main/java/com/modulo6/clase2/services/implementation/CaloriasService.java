package com.modulo6.clase2.services.implementation;

import com.modulo6.clase2.dtos.IngredienteDto;
import com.modulo6.clase2.dtos.IngredienteRespDto;
import com.modulo6.clase2.dtos.PlatoDto;
import com.modulo6.clase2.dtos.RespuestaDto;
import com.modulo6.clase2.repositories.IIngredienteRepository;
import com.modulo6.clase2.services.ICaloriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CaloriasService implements ICaloriasService {

    @Autowired
    IIngredienteRepository iIngredienteRepository;

    @Override
    public RespuestaDto calcularCalorias(PlatoDto plato) {
        Double maxCalorias = 0D;
        List<IngredienteRespDto> ingredientesResp = new ArrayList<>();
        RespuestaDto result = new RespuestaDto();
        result.setCaloriasPlato(0D);
        for (IngredienteDto ingrediente: plato.getIngredientes()) {
            maxCalorias = this.procesarIngrediente(ingrediente, ingredientesResp,
                    maxCalorias, result);
        }
        result.setIngredientes(ingredientesResp);
        return result;
    }

    @Override
    public List<RespuestaDto> procesarPlatos(List<PlatoDto> platos) {
        return platos.stream().map(x -> this.calcularCalorias(x)).collect(Collectors.toList());
    }

    private Double procesarIngrediente(IngredienteDto ingrediente,
                                     List<IngredienteRespDto> ingredientesResp, Double maxCalorias,
                                     RespuestaDto result){
        IngredienteRespDto ingredienteConsultado =
                iIngredienteRepository.consultarIngrediente(ingrediente.getNombre());
        if (Objects.nonNull(ingredienteConsultado)) {
            Double caloriasReferencia = pesoXCaloria(ingrediente.getPeso(), (double) ingredienteConsultado.getCalories());
            result.setCaloriasPlato(result.getCaloriasPlato() + caloriasReferencia);
            IngredienteRespDto ingredienteAux = new IngredienteRespDto();
            ingredienteAux.setCalories(caloriasReferencia);
            ingredienteAux.setName(ingredienteConsultado.getName());
            ingredientesResp.add(ingredienteAux);
            if (caloriasReferencia > maxCalorias) {
                maxCalorias = caloriasReferencia;
                result.setIngredienteMasCalorias(ingredienteConsultado.getName());
            }
        }
        return maxCalorias;
    }

    private Double pesoXCaloria(Double peso, Double calorias) {
        return (peso/100) * calorias;
    }
}
