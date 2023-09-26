package com.example.php_5.service;

import com.example.php_5.models.Telephone;
import com.example.php_5.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelephoneSerice {
    @Autowired
    TelephoneRepository bookRepository;

    public List<Telephone> get_all(){
        ArrayList<Telephone>  books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Telephone get_by_id(int id){
        return bookRepository.findById(id).get();
    }

    public void saveOrUpdate(Telephone books)
    {
        bookRepository.save(books);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        bookRepository.deleteById(id);
    }
    //updating a record
    public void update(Telephone books, int bookid)
    {
        bookRepository.save(books);
    }


}

