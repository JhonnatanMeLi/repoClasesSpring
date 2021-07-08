package com.example.clase4.services.implementation;

import com.example.clase4.dtos.MetricasDto;
import com.example.clase4.dtos.UrlDto;
import com.example.clase4.dtos.UrlRespuestaDto;
import com.example.clase4.entities.Url;
import com.example.clase4.exceptions.PasswordNotValidException;
import com.example.clase4.exceptions.UrlNotExistException;
import com.example.clase4.exceptions.UrlNotValidException;
import com.example.clase4.repositories.IUrlRepository;
import com.example.clase4.services.IUrlService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class UrlService implements IUrlService {

    IUrlRepository urlRepository;

    public UrlService(IUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public UrlRespuestaDto saveUrl(UrlDto urlDto) {
        UrlRespuestaDto result = new UrlRespuestaDto();
        Integer exist = urlRepository.getUrlByUrl(urlDto.getUrl());
        if (exist > 0) {
            result.setMessage("Ya existe una URL almacenada con esa dirección");
            result.setUrl(urlDto.getUrl());
            result.setId(exist);
        } else {
            result.setId(urlRepository.saveUrl(urlDto.getUrl(),
                    Objects.nonNull(urlDto.getPassword()) ? urlDto.getPassword() : null));
            result.setMessage("Se ha creado enmascarado la Url con exito");
            result.setUrl(urlDto.getUrl());
        }
        return result;
    }

    @Override
    public String invalidateUrl(Integer id, String password) throws PasswordNotValidException, UrlNotExistException {
        String result = "";
        String validate = "";
        Url exist = urlRepository.getUrlById(id);
        if (Objects.nonNull(exist)) {
            validate = validatePassword(exist, password);
            if ("".equals(validate)) {
                exist.setValid(false);
                urlRepository.updateUrl(exist, id);
                result = "Se ha invalidado con existo la Url";
            } else
                throw new PasswordNotValidException(validate);
        } else {
            throw new UrlNotExistException("No existe una Url con ese id");
        }
        return result;
    }

    @Override
    public String redirect(Integer id, String password) throws PasswordNotValidException, UrlNotExistException, UrlNotValidException {
        Url exist = urlRepository.getUrlById(id);
        if (Objects.nonNull(exist)) {
            if (!exist.isValid())
                throw new UrlNotValidException("La url consultada no es valida");
            if ("".equals(validatePassword(exist, password))) {
                return exist.getUrl();
            } else
                throw new PasswordNotValidException("La contaseña no coincide");

        } else {
            throw new UrlNotExistException("No existe una Url con ese id");
        }
    }

    @Override
    public MetricasDto metricsUrl(Integer id, String password) {
        return null;
    }

    @Override
    public Map<Integer, Url> getAllUrl() {
        return urlRepository.getUrls();
    }

    private String validatePassword(Url url, String password) {
        String valid = "";
        if (Objects.nonNull(url.getPassword())) {
            valid = url.getPassword().equals(password) ? "" : "La contraseña no coincide";
        } else if(Objects.nonNull(password))
            valid = "La contraseña no es valida";
        else
            valid = "";
        return valid;
    }

}
