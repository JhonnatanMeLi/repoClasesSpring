package com.modulo6.clase2.repositories.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modulo6.clase2.dtos.IngredienteRespDto;
import com.modulo6.clase2.repositories.IIngredienteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class IngredienteRepository implements IIngredienteRepository {

    @Override
    public IngredienteRespDto consultarIngrediente(String nombre) {
        List<IngredienteRespDto> ingredienteDtos;
        ingredienteDtos = loadDataBase();
        IngredienteRespDto result = null;
        if (Objects.nonNull(ingredienteDtos)) {
            Optional<IngredienteRespDto> item = ingredienteDtos
                    .stream().filter(ingrediente ->
                            removerAcentos(ingrediente.getName().toLowerCase())
                            .equals(removerAcentos(nombre.toLowerCase()))).findFirst();
            if (item.isPresent())
                result = item.get();
        }
        return result;
    }

    private String removerAcentos(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private List<IngredienteRespDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/food.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<IngredienteRespDto> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<IngredienteRespDto>> typeReference = new TypeReference<>(){};
        List<IngredienteRespDto> ingredientes = null;
        try {
            ingredientes = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }

}
