package com.example.clase4.services;

import com.example.clase4.dtos.MetricasDto;
import com.example.clase4.dtos.UrlDto;
import com.example.clase4.dtos.UrlRespuestaDto;
import com.example.clase4.entities.Url;
import com.example.clase4.exceptions.PasswordNotValidException;
import com.example.clase4.exceptions.UrlNotExistException;
import com.example.clase4.exceptions.UrlNotValidException;

import java.util.Map;

public interface IUrlService {

    UrlRespuestaDto saveUrl(UrlDto urlDto);

    String invalidateUrl(Integer id, String password) throws PasswordNotValidException, UrlNotExistException;

    String redirect(Integer id, String password) throws PasswordNotValidException, UrlNotExistException, UrlNotValidException;

    MetricasDto metricsUrl(Integer id) throws UrlNotExistException;

    Map<Integer, Url> getAllUrl();

}
