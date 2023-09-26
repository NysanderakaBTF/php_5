package com.example.php_5.controllers;

import com.example.php_5.models.Book;
import com.example.php_5.service.BookSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookComtroller
{
    //autowire the BooksService class
    @Autowired
    BookSerivce booksService;
    //creating a get mapping that retrieves all the books detail from the database
    @GetMapping("/book")
    private List<Book> getAllBooks()
    {
        return booksService.get_all();
    }
    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/book/{bookid}")
    private Book getBooks(@PathVariable("bookid") int bookid)
    {
        return booksService.get_by_id(bookid);
    }
    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/book/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        booksService.delete(bookid);
    }
    //creating post mapping that post the book detail in the database
    @PostMapping("/book")
    private int saveBook(@RequestBody Book books)
    {
        booksService.saveOrUpdate(books);
        return books.getId();
    }
    //creating put mapping that updates the book detail
    @PutMapping("/book")
    private Book update(@RequestBody Book books)
    {
        booksService.saveOrUpdate(books);
        return books;
    }
}