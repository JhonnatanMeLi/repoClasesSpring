package com.example.dependencias.services;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public String responseID() {
        return "Inyección de dependencia completa";
    }
}
