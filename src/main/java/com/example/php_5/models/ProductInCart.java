package com.example.php_5.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class ProductInCart {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int quanity;

    @Column
    private int product_id;

    @Column()
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    @JoinColumn(name = "cart_id", nullable = false, referencedColumnName = "id")
    private Cart cart;
}
