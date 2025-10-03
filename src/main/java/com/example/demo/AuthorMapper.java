package com.example.demo;

import org.springframework.stereotype.Component;

import com.example.demo.dto.AuthorResponseDTO;

@Component
public class AuthorMapper {
    public AuthorResponseDTO toDto(Author author){
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setName(author.getName());
        dto.setId(author.getId());
        return dto;
    }
}
