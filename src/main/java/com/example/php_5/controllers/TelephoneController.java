package com.example.php_5.controllers;

import com.example.php_5.models.Telephone;
import com.example.php_5.service.TelephoneSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TelephoneController
{
    //autowire the BooksService class  
    @Autowired
    TelephoneSerice booksService;
    //creating a get mapping that retrieves all the books detail from the database   
    @GetMapping("/phone")
    private List<Telephone> getAllBooks()
    {
        return booksService.get_all();
    }
    //creating a get mapping that retrieves the detail of a specific book  
    @GetMapping("/phone/{bookid}")
    private Telephone getBooks(@PathVariable("bookid") int bookid)
    {
        return booksService.get_by_id(bookid);
    }
    //creating a delete mapping that deletes a specified book  
    @DeleteMapping("/phone/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        booksService.delete(bookid);
    }
    //creating post mapping that post the book detail in the database  
    @PostMapping("/phone")
    private int saveBook(@RequestBody Telephone books)
    {
        booksService.saveOrUpdate(books);
        return books.getId();
    }
    //creating put mapping that updates the book detail   
    @PutMapping("/phone")
    private Telephone update(@RequestBody Telephone books)
    {
        booksService.saveOrUpdate(books);
        return books;
    }
}  