package com.example.php_5.models;


import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String bookname;
    @Column
    private String author;
    @Column
    private int seller_id;

    @Column()
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.BOOK;

    @Column
    private int price;
}
