package com.example.clase4.controlers;

import com.example.clase4.dtos.MetricasDto;
import com.example.clase4.dtos.UrlDto;
import com.example.clase4.dtos.UrlRespuestaDto;
import com.example.clase4.entities.Url;
import com.example.clase4.exceptions.PasswordNotValidException;
import com.example.clase4.exceptions.UrlNotExistException;
import com.example.clase4.exceptions.UrlNotValidException;
import com.example.clase4.services.IUrlService;
import com.example.clase4.utils.Counter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping
public class UrlController {

    IUrlService urlService;

    public UrlController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/link/save")
    public ResponseEntity<UrlRespuestaDto> saveUrl(@Valid @RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(urlService.saveUrl(urlDto));
    }

    @GetMapping("/link")
    public ResponseEntity<Map<Integer, Url>> getAll() {
        return ResponseEntity.ok(urlService.getAllUrl());
    }

    @GetMapping("/link/{linkId}")
    public ModelAndView redirect(@PathVariable(value = "linkId") Integer id,
                                 @RequestParam(required = false) String password) throws UrlNotExistException, PasswordNotValidException, UrlNotValidException {
        String result = urlService.redirect(id, password);
        Counter.addCounterUrl(id);
        return new ModelAndView("redirect:http://" + result);
    }

    @GetMapping("/metrics/{linkId}")
    public ResponseEntity<MetricasDto> redirect(@PathVariable(value = "linkId") Integer id) throws UrlNotExistException {
        return ResponseEntity.ok(urlService.metricsUrl(id));
    }

    @PatchMapping("/invalidate/{linkId}")
    public ResponseEntity<String> invalidateUrl(@PathVariable(value = "linkId") Integer id,
                                                @RequestParam(required = false) String password) throws UrlNotExistException, PasswordNotValidException {
        return ResponseEntity.ok(urlService.invalidateUrl(id, password));
    }

}
