package com.example.clase4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Url {

    private String url;
    private boolean valid;
    private Integer calls;
    private String password;

}
