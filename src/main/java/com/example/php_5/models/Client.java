package com.example.php_5.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String email;

    @Column
    private String login;

    @Column
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "client")
    private Cart cart;
}
