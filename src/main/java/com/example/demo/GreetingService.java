package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GreetingService {
    private final GreetingRepository greetingRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository, AuthorRepository authorRepository){
        this.greetingRepository = greetingRepository;
        this.authorRepository = authorRepository;
    }

    public Greeting createGreetingForAuthor(long authorId, Greeting greeting){
        Author temp = authorRepository.findById(authorId).orElseThrow(()-> new ResourceNotFoundException("Author not found "+ authorId));
        greeting.setAuthor(temp);
        return greetingRepository.save(greeting);
    }
    
    public Greeting createGreeting(String message){
        Greeting temp = new Greeting();
        temp.setContent(message);
        return greetingRepository.save(temp);
    }
    
    public Greeting getGreetingById(long id){
        return greetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Greeting with id not found " + id));
    }
    
    public Greeting updateById(long id, String content){
        Greeting greetingToUpdate = greetingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Greeting with id not found "+id));
        return greetingRepository.save(greetingToUpdate); 
    }
     
    public void deleteById(long id){
        greetingRepository.deleteById(id);
    }
    
    public List<Greeting> findAll(){
        return greetingRepository.findAll();
    }
}
