package com.modulo6.clase2.services.implementation;

import com.modulo6.clase2.dtos.PriceDto;
import com.modulo6.clase2.dtos.request.CasaDto;
import com.modulo6.clase2.dtos.request.HabitacionDto;
import com.modulo6.clase2.dtos.response.CasaResponseDto;
import com.modulo6.clase2.exceptions.ErrorMessage;
import com.modulo6.clase2.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcularAreaService {

    private final Double VALOR_METRO_CUADRADO = 800D;

    @Autowired
    private PriceRepository priceRepository;

    public CasaResponseDto calcularArea(CasaDto casa) throws ErrorMessage {
        Double areaTotal = 0D;
        Double areaMax = 0D;
        HabitacionDto aux = new HabitacionDto();
        try {
            for (HabitacionDto habitacion: casa.getHabitaciones()) {
                Double area = habitacion.getAncho() * habitacion.getLargo();
                habitacion.setMetrosCuadrados(area);
                areaTotal = areaTotal + area;
                if (area > areaMax) {
                    aux = habitacion;
                    areaMax = area;
                }
            }
            return new CasaResponseDto(areaTotal, areaTotal * VALOR_METRO_CUADRADO, aux, casa.getHabitaciones());
        } catch (Exception e) {
            throw new ErrorMessage("Error en la capa de servicio");
        }
    }

    public PriceDto probarMapper(String location) {
        return priceRepository.encontrarPrecio(location);
    }

}
