package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @NotBlank
    private String content;

    @ManyToOne
    @JsonBackReference
    private Author author;

    public Greeting(){}

    public Greeting(String content){
        this.content = content;
    }

    public Greeting setId(long id){
        this.id = id;
        return this; 
    }

    public Greeting setContent(String content){
        this.content = content;
        return this; 
    }

    public long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

    public Author getAuthor(){
        return author;
    }

    public Author setAuthor(Author author){
        return this.author = author;
    }
}
