package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.AuthorResponseDTO;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;
    
    @Test
    public void findByID_ShouldReturnDto_WhenAuthorExists(){
        Author author = new Author();
        author.setId(1L);
        author.setName("Navneet");
        
        AuthorResponseDTO authorDto = new AuthorResponseDTO();
        authorDto.setId(1L);
        authorDto.setName("Navneet");
        
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        
        when(authorMapper.toDto(author)).thenReturn(authorDto);
        
        AuthorResponseDTO result = authorService.findById(1L);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Navneet", result.getName());
    }

    @Test
    public void findById_ShouldThrowException_WhenAuthorDoesNotExist(){
        long nonExistentId = 99L;

        when(authorRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->{
            authorService.findById(nonExistentId);
        });
    }

    @Test
    public void createAuthor_ShouldSaveAndReturnAuthorDto(){
        String authorName = "Ravi Teja";

        Author authorToSave = new Author();
        authorToSave.setName(authorName);
        
        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.setName(authorName);

        AuthorResponseDTO expectedDto = new AuthorResponseDTO();
        expectedDto.setId(1L);
        expectedDto.setName(authorName);

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        when(authorMapper.toDto(savedAuthor)).thenReturn(expectedDto);

        AuthorResponseDTO result = authorService.createAuthor(authorName);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(authorName, result.getName());

        verify(authorRepository).save(any(Author.class));
    }

    
}

