package com.example.shopfood.interfacee;

import com.example.shopfood.modal.Cart;

import java.util.List;

public interface OrderInterface {
    void listCart(List<Cart> cartList);
    void totalBill(float total, float sub);
    void onMessage();
    void deleteMessage();
}
