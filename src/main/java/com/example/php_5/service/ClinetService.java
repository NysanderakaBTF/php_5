package com.example.php_5.service;

import com.example.php_5.models.Client;
import com.example.php_5.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinetService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> get_all(){
        ArrayList<Client> Clients = new ArrayList<>();
        clientRepository.findAll().forEach(Clients::add);
        return Clients;
    }

    public Client get_by_id(int id){
        return clientRepository.findById(id).get();
    }

    public void saveOrUpdate(Client clients)
    {
        clientRepository.save(clients);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        clientRepository.deleteById(id);
    }
    //updating a record
    public void update(Client clients, int Clientid)
    {
        clientRepository.save(clients);
    }


}
