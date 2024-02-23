package com.example.esun_library;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setISBN(rs.getString("ISBN"));
        book.setName(rs.getString("Name"));
        book.setAuthor(rs.getString("Author"));
        book.setIntroduction(rs.getString("introduction"));

        return book;
    }
}
