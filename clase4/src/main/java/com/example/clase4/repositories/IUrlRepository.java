package com.example.clase4.repositories;

import com.example.clase4.entities.Url;

import java.util.Map;

public interface IUrlRepository {

    Map<Integer, Url> getUrls();

    Integer getUrlByUrl(String Url);

    Url getUrlById(Integer id);

    Integer saveUrl(String url, String password);

    Integer updateUrl(Url url, Integer id);


}
