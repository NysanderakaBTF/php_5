package com.example.php_5.controllers;

import com.example.php_5.models.WashingMachine;
import com.example.php_5.service.WashingMachineSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WashingMachineController
{
    //autowire the BooksService class  
    @Autowired
    WashingMachineSerivce booksService;
    //creating a get mapping that retrieves all the books detail from the database   
    @GetMapping("/wahsingm")
    private List<WashingMachine> getAllBooks()
    {
        return booksService.get_all();
    }
    //creating a get mapping that retrieves the detail of a specific book  
    @GetMapping("/wahsingm/{bookid}")
    private WashingMachine getBooks(@PathVariable("bookid") int bookid)
    {
        return booksService.get_by_id(bookid);
    }
    //creating a delete mapping that deletes a specified book  
    @DeleteMapping("/wahsingm/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        booksService.delete(bookid);
    }
    //creating post mapping that post the book detail in the database  
    @PostMapping("/wahsingm")
    private int saveBook(@RequestBody WashingMachine books)
    {
        booksService.saveOrUpdate(books);
        return books.getId();
    }
    //creating put mapping that updates the book detail   
    @PutMapping("/wahsingm")
    private WashingMachine update(@RequestBody WashingMachine books)
    {
        booksService.saveOrUpdate(books);
        return books;
    }
}  
