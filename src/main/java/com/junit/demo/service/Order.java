package com.junit.demo.service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class Order {

    private String dishName;

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;


    public Order(String dishName, BigDecimal price) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
