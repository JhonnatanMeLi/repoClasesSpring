package com.example.clase4.utils;

import com.example.clase4.repositories.UrlRepository;

public final class Counter {

    public static void addCounterUrl(Integer id) {
        UrlRepository.addCounter(id);
    }

}
