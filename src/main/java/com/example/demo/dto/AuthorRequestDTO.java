package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequestDTO {
    @NotBlank
    private String name;

    public String getName(){
        return name;
    }
    
    public String setName(String name){
        return this.name=name;
    }
}
