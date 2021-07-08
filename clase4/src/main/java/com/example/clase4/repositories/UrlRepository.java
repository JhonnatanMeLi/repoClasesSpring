package com.example.clase4.repositories;

import com.example.clase4.entities.Url;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepository implements IUrlRepository {

    private static Map<Integer, Url> bd = new HashMap<>();
    private static Integer contador = 0;

    @Override
    public Map<Integer, Url> getUrls() {
        return this.bd;
    }

    @Override
    public Integer getUrlByUrl(String url) {
        Integer result = 0;
        for (Map.Entry<Integer, Url> entry : this.bd.entrySet()) {
            result = entry.getValue().getUrl().equals(url) ? entry.getKey() : result;
        }
        return result;
    }

    @Override
    public Url getUrlById(Integer id) {
        return this.bd.get(id);
    }

    @Override
    public Integer saveUrl(String url, String password) {
        this.bd.put(++this.contador, new Url(url, true, 0, password));
        return this.contador;
    }

    @Override
    public Integer updateUrl(Url url, Integer id) {
        this.bd.put(id, url);
        return id;
    }

    public static void addCounter(Integer id) {
        bd.get(id).setCalls(bd.get(id).getCalls()+1);
    }
}
