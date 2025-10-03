    package com.example.demo;

    import java.util.ArrayList;
    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.example.demo.dto.AuthorResponseDTO;


    @Service
    public class AuthorService {

        private final AuthorRepository authorRepository;
        private final AuthorMapper authorMapper;

        @Autowired
        public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper){
            this.authorRepository = authorRepository;
            this.authorMapper = authorMapper;
        }

        public AuthorResponseDTO createAuthor(String name){
            Author temp = new Author();
            temp.setName(name);
            Author savedAuthor = authorRepository.save(temp);
            return authorMapper.toDto(savedAuthor);
        }

        public List<AuthorResponseDTO> findAll(){
            List<Author> temp =  authorRepository.findAll();                   
            List<AuthorResponseDTO> tempo = new ArrayList<AuthorResponseDTO>();
            for(int i=0;i<temp.size();++i){
                tempo.add(authorMapper.toDto(temp.get(i)));
            }
            return tempo;
        }

        public void deleteById(long id){
            authorRepository.deleteById(id);
        }

        public AuthorResponseDTO findById(long id){
            Author temp = authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author with id not found "+id));
            return authorMapper.toDto(temp);
        }

        public AuthorResponseDTO updateById(long id, String name){
            Author updatedAuthor = authorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Author with id not found "+id));
            updatedAuthor.setName(name);
            authorRepository.save(updatedAuthor);
            return authorMapper.toDto(updatedAuthor);
        }
    }

