package com.example.demo.dto;

public class AuthorResponseDTO {
    private long id;
    private String name;

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public long setId(long id){
        return this.id = id;
    }

    public String setName(String name){
        return this.name = name;
    }
}
