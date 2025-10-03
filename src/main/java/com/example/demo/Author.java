package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Greeting> greetings = new ArrayList<>();

    public Author(){}   

    public String getName(){
        return name;
    }

    public String setName(String name){
        return this.name = name;
    }  

    public long setId(long id){
        return this.id = id;
    }

    public long getId(){
        return id;
    }

    public List<Greeting> getGreetings(){
        return greetings;
    }
}
