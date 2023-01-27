package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.Person;
import com.example.library.service.BookService;
import com.example.library.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @GetMapping
    public String viewListBook(Model model) {
        List<Book> listBook = bookService.findAllBook();
        model.addAttribute("listBook", listBook);
        return "listBook";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        Book book = bookService.getBook(id);
        List<Person> listPerson = personService.findAllPerson();
        model.addAttribute("book", book);
        model.addAttribute("listPerson", listPerson);
        if (book.getPerson() != null) {
            Person person = personService.getPerson(book.getPerson().getId());
            model.addAttribute("person", person);
        }
        return "book";
    }

    @GetMapping("/new")
    public String viewCreateBook(Model model) {
        model.addAttribute("book", new Book());
        return "createBook";
    }

    @PostMapping("/new")
    public String createBook(@Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "createBook";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String viewEditBook(@PathVariable int id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook(@Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "editBook";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/appoint")
    public String appointBook(@RequestParam int idBook, @RequestParam int idPerson) {
        Person person = personService.getPerson(idPerson);
        Book book = bookService.getBook(idBook);
        book.setPerson(person);
        person.getBookList().add(book);
        personService.editPerson(person);
        bookService.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("/release")
    public String releaseBook(@RequestParam int idBook, @RequestParam int idPerson) {
        Person person = personService.getPerson(idPerson);
        Book book = bookService.getBook(idBook);
        book.setPerson(null);
        person.getBookList().remove(book);
        personService.editPerson(person);
        bookService.createBook(book);
        return "redirect:/books";
    }
}
