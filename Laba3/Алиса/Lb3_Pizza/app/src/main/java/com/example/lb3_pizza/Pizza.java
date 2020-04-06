package com.example.lb3_pizza;

public class Pizza {

    double pizza_size_price;
    double meat_price = 0;
    double cheese_price = 0;
    double mango_price = 0;
    double nut_price = 0;

    public Pizza() {

    }

    public double getPizza_size_price() {
        return pizza_size_price;
    }

    public void setPizza_size_price(double pizza_size_price) {
        this.pizza_size_price = pizza_size_price;
    }

    public double getMeat_price() {
        return meat_price;
    }

    public void setMeat_price(double meat_price) {
        this.meat_price = meat_price;
    }

    public double getCheese_price() {
        return cheese_price;
    }

    public void setCheese_price(double cheese_price) {
        this.cheese_price = cheese_price;
    }

    public double getMango_price() {
        return mango_price;
    }

    public void setMango_price(double mango_price) {
        this.mango_price = mango_price;
    }

    public double getNut_price() {
        return nut_price;
    }

    public void setNut_price(double nut_price) {
        this.nut_price = nut_price;
    }
}
