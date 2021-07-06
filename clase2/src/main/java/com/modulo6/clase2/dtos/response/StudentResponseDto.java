package com.modulo6.clase2.dtos.response;

import lombok.Data;

@Data
public class StudentResponseDto {

    private String validate;
    private String save;

    public StudentResponseDto(String validateStudent, String saveStudent) {
    }
}
