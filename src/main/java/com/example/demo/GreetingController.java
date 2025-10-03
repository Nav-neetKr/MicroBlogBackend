package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class GreetingController {
    
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @PostMapping("/greetings")
    public Greeting addGreetings(@Valid @RequestBody Greeting message ) {
        return greetingService.createGreeting(message.getContent());
    }
    
    @GetMapping("/greetings")
    public List<Greeting> getGreetings() {
        return greetingService.findAll();
    }

    @GetMapping("/greetings/{id}")
    public Greeting getGreetingsById(@PathVariable long id){
        return greetingService.getGreetingById(id);
    }

    @PutMapping("/greetings/{id}")
    public Greeting updateById(@Valid @PathVariable long id, @RequestBody Greeting greetingContent) {
        return greetingService.updateById(id, greetingContent.getContent());
    }

    @DeleteMapping("/greetings/{id}")
    public void deleteById(@PathVariable long id){
        greetingService.deleteById(id);
    }

    @PostMapping("/authors/{authorId}/greetings")
    public Greeting createGreetingForAuthor(@PathVariable long authorId, @Valid @RequestBody Greeting greeting) {
        return greetingService.createGreetingForAuthor(authorId, greeting);
    }
    
}
