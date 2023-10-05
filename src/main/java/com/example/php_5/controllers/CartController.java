package com.example.php_5.controllers;


import com.example.php_5.models.Cart;
import com.example.php_5.models.ProductInCart;
import com.example.php_5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart/{id}")
    public Cart get_by_id (@PathVariable("id") int id){
        return cartService.get_by_id(id);
    }

    @PostMapping("/cart/{user_id}")
    public Cart add_product(@PathVariable("user_id") int uid,  @RequestBody ProductInCart product){

        int res =  cartService.addProductToCart(uid, product.getProduct_id(), String.valueOf(product.getProductType()), product.getQuanity());
        if (res <0){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "not enoughh products");
        }
        return cartService.get_by_id(uid);
    }

    @DeleteMapping("/cart/{product_id}")
    public void delete_product(@PathVariable("product_id") int product_id){
        cartService.delete_product_in_cart(product_id);
    }

    @PutMapping("/cart/{prodcut_in_cart_id}")
    public void update_q(@PathVariable("prodcut_in_cart_id") int pid, @RequestParam("quantity") int q){
        cartService.change_quantity(pid,q);
    }

    @PostMapping("/cart/buy/{user_id}")
    public String buy(@PathVariable("user_id") int pid){
        return cartService.buy(pid);
    }

}
