package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBook() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
