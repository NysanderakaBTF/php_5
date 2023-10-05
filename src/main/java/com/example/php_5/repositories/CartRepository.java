package com.example.php_5.repositories;

import com.example.php_5.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByClient_Id (Integer client_id);
}
