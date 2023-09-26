package com.example.php_5.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Telephone {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String manufacturer;

    @Column
    private int battery;
    @Column
    private int seller_id;

    @Column()
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.PHONE;

    @Column
    private int price;
}
