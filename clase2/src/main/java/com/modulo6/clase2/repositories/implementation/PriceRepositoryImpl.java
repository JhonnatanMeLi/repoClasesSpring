package com.modulo6.clase2.repositories.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modulo6.clase2.dtos.PriceDto;
import com.modulo6.clase2.repositories.PriceRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepository {


    @Override
    public PriceDto encontrarPrecio(String location) {
        List<PriceDto> priceDtos;
        priceDtos = loadDataBase();
        PriceDto result = null;
        if (Objects.nonNull(priceDtos)) {
            Optional<PriceDto> item = priceDtos.stream().filter(priceDto -> priceDto.getLocation().equals(location)).findFirst();
            if (item.isPresent())
                result = item.get();
        }
        return result;
    }

    private List<PriceDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/prices.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<PriceDto> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<PriceDto>> typeReference = new TypeReference<>(){};
        List<PriceDto> priceDtos = null;
        try {
            priceDtos = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceDtos;
    }
}
