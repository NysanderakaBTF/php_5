package com.example.php_5.service;

import com.example.php_5.models.Book;
import com.example.php_5.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookSerivce {
    @Autowired
    BookRepository bookRepository;

    public List<Book> get_all(){
        ArrayList<Book>  books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book get_by_id(int id){
        return bookRepository.findById(id).get();
    }

    public void saveOrUpdate(Book books)
    {
        bookRepository.save(books);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        bookRepository.deleteById(id);
    }
    //updating a record
    public void update(Book books, int bookid)
    {
        bookRepository.save(books);
    }


}
