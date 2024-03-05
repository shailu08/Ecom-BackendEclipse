package com.apogee.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apogee.payload.CartDto;
import com.apogee.payload.ItemRequest;
import com.apogee.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    private CartService cartSer;

//    http://localhost:8080/EcomBack/cart/addtoCart
//    
//    {
//    "productId":5,
//    "quantity":10
//}
    @PostMapping("/addtoCart")
    public ResponseEntity<CartDto> addtoCart(@RequestBody ItemRequest itemRequest, Principal principal) {
//        String email = principal.getName();
        String email = "sh@gmail.com";
        System.out.println(email);
//        CartDto addItem = this.cartSer.addItem(itemRequest, principal.getName());
        CartDto addItem = this.cartSer.addItem(itemRequest, email);
        return new ResponseEntity<CartDto>(addItem, HttpStatus.OK);
    }

//    http://localhost:8080/EcomBack/cart/getCart
    //create method for getting cart
    @GetMapping("/getCart")
    public ResponseEntity<CartDto> getAllCart(Principal principal) {
//        CartDto getcartAll = this.cartSer.getcartAll(principal.getName());
        CartDto getcartAll = this.cartSer.getcartAll("sh@gmail.com");

        return new ResponseEntity<CartDto>(getcartAll, HttpStatus.ACCEPTED);
    }

//    http://localhost:8080/EcomBack/cart/getCartById/1
    @GetMapping("getCartById/{cartId}") 
    public ResponseEntity<CartDto> getCartById(@PathVariable int cartId) {

        System.out.println(cartId);
        CartDto cartByID = this.cartSer.getCartByID(cartId);
        return new ResponseEntity<CartDto>(cartByID, HttpStatus.OK);
    }

    @DeleteMapping("deleteCartItemFromCart/{pid}")
    public CartDto deleteCartItemFromCart(@PathVariable int pid, Principal p) {

//        CartDto remove = this.cartSer.removeCartItemFromCart(p.getName(), pid);
        CartDto remove = this.cartSer.removeCartItemFromCart("sh@gmail.com", pid);

        return remove;
    }
}
