package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.demo.dto.AuthorRequestDTO;
import com.example.demo.dto.AuthorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void getAuthorById_ShouldReturnAuthorDTO() throws Exception {
        long authorId = 1L;
        AuthorResponseDTO authorDto = new AuthorResponseDTO();
        authorDto.setId(authorId);
        authorDto.setName("Test Author");

        when(authorService.findById(authorId)).thenReturn(authorDto);

        mockMvc.perform(get("/authors/{id}",authorId)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(authorId)).andExpect(jsonPath("$.name").value("Test Author")); 
    }

    @Test
    public void createAuthor_ShouldCreateAndReturnAuthorDto() throws Exception {
        AuthorRequestDTO requestDTO = new AuthorRequestDTO();
        requestDTO.setName("New Author");

        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("New Author");

        when(authorService.createAuthor(any(String.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(requestDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L)) 
        .andExpect(jsonPath("$.name").value("New Author"));
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createAuthor_ShouldReturnBadRequest_WhenNameIsBlank() throws Exception {
        AuthorRequestDTO requestDTO = new AuthorRequestDTO();
        requestDTO.setName("");
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestDTO)))
                        .andExpect(status().isBadRequest());
    }
}

