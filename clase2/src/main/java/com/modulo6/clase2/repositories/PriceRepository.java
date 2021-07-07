package com.modulo6.clase2.repositories;

import com.modulo6.clase2.dtos.PriceDto;

public interface PriceRepository {

    PriceDto encontrarPrecio(String location);

}
