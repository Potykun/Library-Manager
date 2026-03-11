package com.rest_example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest_example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
