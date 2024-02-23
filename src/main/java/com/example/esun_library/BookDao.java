package com.example.esun_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Book findByBookName(String Name){
        String sql = "SELECT ISBN, Name, Author, introduction  FROM book WHERE Name LIKE :Name";
        Map<String, Object> map = new HashMap<>();
        map.put("Name","%" + Name + "%");

        List<Book> bookList = namedParameterJdbcTemplate.query(sql, map, new BookRowMapper());

        if (bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }
    }

}
