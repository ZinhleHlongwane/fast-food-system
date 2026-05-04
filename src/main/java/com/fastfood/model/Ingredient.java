package com.fastfood.model;

public class Ingredient {

    private String name;
    private int quantity;

    public Ingredient(String name, int quantity) {
        validateQuantity(quantity);
        this.name = name;
        this.quantity = quantity;
    }

    public String name() {
        return this.name;
    }

    public int quantity() {
        return this.quantity;
    }

    public void updateQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
    }

    @Override
    public String toString() {
        return "Name: " + name() + ", Quantity: " + quantity();
    }
}