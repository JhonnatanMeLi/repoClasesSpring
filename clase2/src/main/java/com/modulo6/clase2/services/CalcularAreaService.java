package com.modulo6.clase2.services;

import com.modulo6.clase2.dtos.request.CasaDto;
import com.modulo6.clase2.dtos.request.HabitacionDto;
import com.modulo6.clase2.dtos.response.CasaResponseDto;
import com.modulo6.clase2.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
public class CalcularAreaService {

    private final Double VALOR_METRO_CUADRADO = 800D;

    public CasaResponseDto calcularArea(CasaDto casa) throws ErrorMessage {
        Double areaTotal = 0D;
        Double areaMax = 0D;
        Double valorTotal = 0D;
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
            valorTotal = areaTotal * VALOR_METRO_CUADRADO;
            return new CasaResponseDto(areaTotal, valorTotal, aux, casa.getHabitaciones());
        } catch (Exception e) {
            throw new ErrorMessage("Error en la capa de servicio");
        }
    }

}
