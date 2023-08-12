package com.example.shopfood.modal;

import java.util.List;

public class StatusFood {
    private String status;
    public List<Food> foodList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "StatusFood{" +
                "status='" + status + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}
