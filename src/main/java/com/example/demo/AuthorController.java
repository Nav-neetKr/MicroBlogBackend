package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthorRequestDTO;
import com.example.demo.dto.AuthorResponseDTO;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/authors")
    public AuthorResponseDTO postAuthor(@Valid @RequestBody AuthorRequestDTO author) {
        return authorService.createAuthor(author.getName());
    }

    @GetMapping("/authors/{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable long id) {
        return authorService.findById(id);
    }
    
    @GetMapping("/authors")
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorService.findAll();
    }
    
    @PutMapping("/authors/{id}")
    public AuthorResponseDTO updateById(@PathVariable long id, @RequestBody AuthorRequestDTO authorDetails) {
        return authorService.updateById(id, authorDetails.getName());
    }

    @DeleteMapping("/authors/{id}")
    public void deleteById(@PathVariable long id){
        authorService.deleteById(id);
    }
}
