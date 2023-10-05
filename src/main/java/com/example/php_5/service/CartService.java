package com.example.php_5.service;


import com.example.php_5.models.*;
import com.example.php_5.repositories.CartRepository;
import com.example.php_5.repositories.ProductInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    BookSerivce bookSerivce;

    @Autowired
    TelephoneSerice telephoneSerice;

    @Autowired
    WashingMachineSerivce washingMachineSerivce;

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Autowired
    CartRepository cartRepository;

    public Cart create_update_cart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    public List<Cart> get_all() {
        return new ArrayList<>(cartRepository.findAll());
    }

    public Cart get_by_id(int id) {
        return cartRepository.findById(id).get();
    }

    public void saveOrUpdate(Cart clients) {
        cartRepository.save(clients);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id) {
        cartRepository.deleteById(id);
    }

    //updating a record
    public void update(Cart clients, int Clientid) {
        cartRepository.save(clients);
    }


    public void recalculateTotal(int cart_id) {
        List<ProductInCart> products = productInCartRepository.findProductInCartByCart_Id(cart_id);
        Cart cart = this.get_by_id(cart_id);
        int sum = 0;
        for (var i : products) {
            if (i.getProductType() == ProductType.BOOK) {
                Book book = bookSerivce.get_by_id(i.getProduct_id());
                sum += book.getPrice() + i.getQuanity();
            } else if (i.getProductType() == ProductType.PHONE) {
                Telephone telephone = telephoneSerice.get_by_id(i.getProduct_id());
                sum += telephone.getPrice() * i.getQuanity();
            } else if (i.getProductType() == ProductType.WAHSING_MASHINE) {
                WashingMachine washingMachine = washingMachineSerivce.get_by_id(i.getProduct_id());
                sum += washingMachine.getPrice() * i.getQuanity();
            }
        }
        cart.setTotal(sum);
        cartRepository.save(cart);
    }

    public int addProductToCart(int user_id, int product_id, String product_type, int quantity){
        Cart cart = cartRepository.getCartByClient_Id(user_id);
        ProductInCart product = new ProductInCart();
        product.setCart(cart);
        product.setProduct_id(product_id);
        product.setQuanity(quantity);

        if (product_type.equalsIgnoreCase(String.valueOf(ProductType.BOOK))) {
            Book book = bookSerivce.get_by_id(product_id);
            if (quantity>book.getTotal_q())
                return -1;
            product.setProductType(ProductType.BOOK);
        } else if (product_type.equalsIgnoreCase(String.valueOf(ProductType.PHONE))) {
            Telephone book = telephoneSerice.get_by_id(product_id);
            if (quantity>book.getTotal_q())
                return -1;
            product.setProductType(ProductType.PHONE);
        } else if (product_type.equalsIgnoreCase(String.valueOf(ProductType.WAHSING_MASHINE))) {
            WashingMachine book = washingMachineSerivce.get_by_id(product_id);
            if (quantity>book.getTotal_q())
                return -1;
            product.setProductType(ProductType.WAHSING_MASHINE);
        }

        productInCartRepository.save(product);
        return 1;
    }

    public void change_quantity(int product_in_cart_id, int q){
        ProductInCart product = productInCartRepository.getById(product_in_cart_id);
        int available_q = 0;

        if (product.getProductType() == ProductType.BOOK) {
            Book book = bookSerivce.get_by_id(product.getProduct_id());
            available_q = book.getTotal_q();
        } else if (product.getProductType() == ProductType.PHONE) {
            Telephone telephone = telephoneSerice.get_by_id(product.getProduct_id());
            available_q = telephone.getTotal_q();
        } else if (product.getProductType() == ProductType.WAHSING_MASHINE) {
            WashingMachine washingMachine = washingMachineSerivce.get_by_id(product.getProduct_id());
            available_q = washingMachine.getTotal_q();
        }

        if(available_q>q){
            return;
        }
        product.setQuanity(q);
        productInCartRepository.save(product);
        this.recalculateTotal(product.getCart().getId());
    }
    public void delete_product_in_cart(int product_ic_id){
        productInCartRepository.deleteById(product_ic_id);
    }

    public String buy(int user_id){
        Cart cart = cartRepository.getCartByClient_Id(user_id);
        // check availability
        for(var i: cart.getProducts()){
            if (i.getProductType() == ProductType.BOOK) {
                Book book = bookSerivce.get_by_id(i.getProduct_id());
                if(i.getQuanity()>book.getTotal_q()){
                    return "Not enough books";
                }
                book.setTotal_q(book.getTotal_q()-i.getQuanity());
            } else if (i.getProductType() == ProductType.PHONE) {
                Telephone telephone = telephoneSerice.get_by_id(i.getProduct_id());
                if(i.getQuanity()>telephone.getTotal_q()){
                    return "Not enough phones";
                }
                telephone.setTotal_q(telephone.getTotal_q()-i.getQuanity());
            } else if (i.getProductType() == ProductType.WAHSING_MASHINE) {
                WashingMachine washingMachine = washingMachineSerivce.get_by_id(i.getProduct_id());
                if(i.getQuanity()>washingMachine.getTotal_q()){
                    return "Not enough washing machnes";
                }
                washingMachine.setTotal_q(washingMachine.getTotal_q()-i.getQuanity());
            }
        }

        productInCartRepository.deleteAll(cart.getProducts());
        this.recalculateTotal(cart.getId());
        return "OK";
    }

}
