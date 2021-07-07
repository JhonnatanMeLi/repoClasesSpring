package com.modulo6.clase2.services.implementation;

import com.modulo6.clase2.dtos.response.EdadDto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class CalcularEdad {

    public EdadDto calcularEdad(Integer dia, Integer mes, Integer anio) {
        if (!validarFecha(dia, mes, anio))
            return new EdadDto(-1D, "Fecha invalida");
        GregorianCalendar fecha = new GregorianCalendar();
        fecha.setLenient(false);
        fecha = new GregorianCalendar(anio, mes - 1, dia);
        try {
            Long milis = (new Date()).getTime() - fecha.getTime().getTime();
            Double anios = milis / 31536000000D;
            return new EdadDto(Math.floor(anios), "Todo bien, todo fine");
        } catch (Exception e) {
            return new EdadDto(-1D, "Fecha invalida");
        }
    }

    private boolean validarFecha(Integer dia, Integer mes, Integer anio) {
        return dia < 0 || dia > 31 || mes < 1 || mes > 12 || anio < 1900
                || anio > Calendar.getInstance().get(Calendar.YEAR);
    }

}
