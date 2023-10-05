package com.example.php_5.controllers;
import com.example.php_5.models.Cart;
import com.example.php_5.models.Client;
import com.example.php_5.service.CartService;
import com.example.php_5.service.ClinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ClientController
{
    //autowire the BooksService class
    @Autowired
    ClinetService booksService;

    @Autowired
    CartService cartService;
    //creating a get mapping that retrieves all the books detail from the database
    @GetMapping("/client")
    private List<Client> getAllBooks()
    {
        return booksService.get_all();
    }
    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/client/{bookid}")
    private Client getBooks(@PathVariable("bookid") int bookid)
    {
        return booksService.get_by_id(bookid);
    }
    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/client/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        booksService.delete(bookid);
    }
    //creating post mapping that post the book detail in the database
    @PostMapping("/client")
    private int saveBook(@RequestBody Client books)
    {
        booksService.saveOrUpdate(books);
        Cart cart = new Cart();
        cart.setClient(books);
        cart.setTotal(0);
        cartService.saveOrUpdate(cart);
        return books.getId();
    }
    //creating put mapping that updates the book detail
    @PutMapping("/client")
    private Client update(@RequestBody Client books)
    {
        booksService.saveOrUpdate(books);
        return books;
    }
}