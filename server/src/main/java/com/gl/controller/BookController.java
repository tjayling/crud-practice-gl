package com.gl.controller;

import com.gl.model.Book;
import com.gl.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.BadRequestException;

import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @PostMapping
    public Book createBook(@NonNull @RequestBody Book book) {
        return bookRepo.save(book);
    }

    @GetMapping
    public List<Book> getBook() {
        return bookRepo.findAll();
    }

    @GetMapping(path = "{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookRepo.findById(id).get();
    }

    @GetMapping(path = "/find-by-title/{title}")
    public Book getBookByTitle(@PathVariable("title") String title) throws Exception {
        if (title == null) throw new BadRequestException("Book title cannot be empty");
        if (bookRepo.findBookByTitle(title).isEmpty()) {
            throw new NotFoundException("Book with title " + title + " does not exist");
        }

        return bookRepo.findBookByTitle(title).get(0);
    }

    @PutMapping(path = "{id}")
    public void updateBook(@PathVariable("id") Long id, @NonNull @RequestBody Book book) throws
            Exception {

        if (book.getId() == null) {

            throw new BadRequestException("Book ID must not be null");
        }
        Optional<Book> optionalBook = bookRepo.findById(book.getId());
        if (optionalBook.isEmpty()) {
            throw new NotFoundException("Book with ID " + id + " does not exist");
        }

        Book existingBook = optionalBook.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setYear(book.getYear());
        existingBook.setPages(book.getPages());
        bookRepo.save(existingBook);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBookById(@PathVariable("id") Long id) throws NotFoundException {
        if (bookRepo.findById(id).isEmpty()) {
            throw new NotFoundException("Book with ID " + id + " does not exist");
        }
        bookRepo.deleteById(id);
    }
}
