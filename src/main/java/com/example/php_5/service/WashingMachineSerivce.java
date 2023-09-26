package com.example.php_5.service;

import com.example.php_5.models.WashingMachine;
import com.example.php_5.repositories.WahingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WashingMachineSerivce {
    @Autowired
    WahingMachineRepository bookRepository;

    public List<WashingMachine> get_all(){
        ArrayList<WashingMachine>  books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public WashingMachine get_by_id(int id){
        return bookRepository.findById(id).get();
    }

    public void saveOrUpdate(WashingMachine books)
    {
        bookRepository.save(books);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        bookRepository.deleteById(id);
    }
    //updating a record
    public void update(WashingMachine books, int bookid)
    {
        bookRepository.save(books);
    }


}
