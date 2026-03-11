package com.rest_example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest_example.demo.model.Book;
import com.rest_example.demo.repository.BookRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Library API", description = "CRUD operations for books")
public class BookController {
    private final BookRepository repository;

    @GetMapping
    public List<Book> getAll() { return repository.findAll(); }

    @PostMapping
    public Book create(@Valid @RequestBody Book book) { return repository.save(book); }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable Integer id) { 
        return repository.findById(id).orElseThrow(); 
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @Valid @RequestBody Book details) {
        Book book = repository.findById(id).orElseThrow();
        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setPublicationYear(details.getPublicationYear());
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { repository.deleteById(id); }
}