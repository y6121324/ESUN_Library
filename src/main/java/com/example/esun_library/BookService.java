package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {
    @Autowired BookDao bookDao;
    public  Book findByBookName (String Name){
        return bookDao.findByBookName(Name);
    }
}

