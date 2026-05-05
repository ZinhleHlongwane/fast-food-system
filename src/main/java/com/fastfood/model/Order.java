package com.fastfood.model;

public class Order {

    private int orderId;
    private String customerName;
    private Meal meal;
    private OrderStatus status;

    public enum OrderStatus {

        PENDING,
        PREPARING,
        COMPLETED,
        CANCELLED
    }

    public Order(int orderId, String customerName, Meal meal) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.meal = meal;
        this.status = OrderStatus.PENDING;
    }

    public int orderId() {
        return this.orderId;
    }

    public String customerName() {
        return this.customerName;
    }

    public String customer() {
        return this.customerName();
    }

    public Meal meal() {
        return this.meal;
    }

    public OrderStatus status() {
        return this.status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " | " + customerName + " | " + meal.name() + " | " + status;
    }
}