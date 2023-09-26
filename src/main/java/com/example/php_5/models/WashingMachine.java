package com.example.php_5.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class WashingMachine {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String manufacturer;

    @Column
    private int volume;
    @Column
    private int seller_id;

    @Column()
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.WAHSING_MASHINE;

    @Column
    private int price;
}
