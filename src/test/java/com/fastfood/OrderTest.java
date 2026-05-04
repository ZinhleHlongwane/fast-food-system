package com.fastfood;

import com.fastfood.model.Ingredient;
import com.fastfood.model.Meal;
import com.fastfood.model.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Meal sampleMeal() {
        return new Meal("Cheeseburger",
                List.of(
                        new Ingredient("Bun", 2),
                        new Ingredient("Patty", 1)
                ));
    }

    @Test
    void constructor_setsFieldsCorrectly() {
        Order order = new Order(1, "Alice", sampleMeal());

        assertEquals(1, order.orderId());
        assertEquals("Alice", order.customerName());
        assertEquals("Alice", order.customer());
        assertEquals("Cheeseburger", order.meal().name());
    }

    @Test
    void constructor_defaultsStatusToPending() {
        Order order = new Order(1, "Alice", sampleMeal());

        assertEquals(Order.OrderStatus.PENDING, order.status());
    }

    @Test
    void updateStatus_updatesToPreparing() {
        Order order = new Order(2, "Bob", sampleMeal());

        order.updateStatus(Order.OrderStatus.PREPARING);

        assertEquals(Order.OrderStatus.PREPARING, order.status());
    }

    @Test
    void updateStatus_updatesToCompleted() {
        Order order = new Order(3, "Carol", sampleMeal());

        order.updateStatus(Order.OrderStatus.COMPLETED);

        assertEquals(Order.OrderStatus.COMPLETED, order.status());
    }

    @Test
    void updateStatus_updatesToCancelled() {
        Order order = new Order(4, "Dave", sampleMeal());

        order.updateStatus(Order.OrderStatus.CANCELLED);

        assertEquals(Order.OrderStatus.CANCELLED, order.status());
    }

    @Test
    void orderStatus_hasAllFourValues() {
        Order.OrderStatus[] values = Order.OrderStatus.values();

        assertEquals(4, values.length);
    }

    @Test
    void toString_containsOrderDetails() {
        Order order = new Order(5, "Eve", sampleMeal());

        String result = order.toString();

        assertTrue(result.contains("Eve"));
        assertTrue(result.contains("Cheeseburger"));
        assertTrue(result.contains("PENDING"));
    }
}