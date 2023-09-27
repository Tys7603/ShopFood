package com.example.shopfood.modal;

import java.util.List;

public class StatusCart {
    private String status;

    private List<Cart> cartList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
