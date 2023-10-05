package com.example.php_5.repositories;

import com.example.php_5.models.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInCartRepository extends JpaRepository<ProductInCart, Integer> {
    List<ProductInCart> findProductInCartByCart_Id(Integer cart_id);
}
