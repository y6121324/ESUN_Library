package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired BookService bookService;

    @GetMapping ("/books/{Name}")
    public ResponseEntity<Book> BookSearch(@PathVariable String Name){
        Book book = bookService.findByBookName(Name);
        if (book != null) {
           return ResponseEntity.status(HttpStatus.OK).body(book);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }
}
