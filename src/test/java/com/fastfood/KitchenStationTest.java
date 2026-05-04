package com.fastfood;

import com.fastfood.model.Ingredient;
import com.fastfood.model.Meal;
import com.fastfood.model.Order;
import com.fastfood.service.BurgerStation;
import com.fastfood.service.KitchenStation;
import com.fastfood.service.PizzaStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KitchenStationTest {

    private BurgerStation burgerStation;
    private PizzaStation pizzaStation;
    private Meal cheeseburger;
    private Meal pepperoniPizza;

    @BeforeEach
    void setUp() {
        burgerStation = new BurgerStation("GrillMaster");
        pizzaStation = new PizzaStation("PizzaHub");

        cheeseburger = new Meal("Cheeseburger",
                List.of(
                        new Ingredient("Bun", 2),
                        new Ingredient("Patty", 1),
                        new Ingredient("Cheese", 1)
                ));

        pepperoniPizza = new Meal("Pepperoni Pizza",
                List.of(
                        new Ingredient("Dough", 1),
                        new Ingredient("Cheese", 1),
                        new Ingredient("Pepperoni", 8)
                ));
    }

    @Test
    void addMealAndRetrieveByName() {
        burgerStation.addMeal(cheeseburger);

        Meal found = burgerStation.getMeal("Cheeseburger");

        assertNotNull(found);
        assertEquals("Cheeseburger", found.name());
    }

    @Test
    void getMealReturnsNullForUnknownName() {
        assertNull(burgerStation.getMeal("Unknown Meal"));
    }

    @Test
    void getAllMealsReturnsAllAddedMeals() {
        burgerStation.addMeal(cheeseburger);
        burgerStation.addMeal(new Meal("Chicken Burger",
                List.of(new Ingredient("Chicken Patty", 1))));

        assertEquals(2, burgerStation.getAllMeals().size());
    }

    @Test
    void getAllMealsReturnsUnmodifiableMap() {
        burgerStation.addMeal(cheeseburger);

        assertThrows(UnsupportedOperationException.class,
                () -> burgerStation.getAllMeals().put("Hack", cheeseburger));
    }

    @Test
    void placeOrderCreatesOrderWithCorrectDetails() {
        burgerStation.addMeal(cheeseburger);

        Order order = burgerStation.placeOrder("Alice", "Cheeseburger");

        assertNotNull(order);
        assertEquals("Alice", order.customerName());
        assertEquals("Cheeseburger", order.meal().name());
        assertEquals(Order.OrderStatus.PENDING, order.status());
    }

    @Test
    void placeOrderAssignsUniqueIncrementingIds() {
        burgerStation.addMeal(cheeseburger);

        Order o1 = burgerStation.placeOrder("Alice", "Cheeseburger");
        Order o2 = burgerStation.placeOrder("Bob", "Cheeseburger");

        assertNotEquals(o1.orderId(), o2.orderId());
        assertEquals(o1.orderId() + 1, o2.orderId());
    }

    @Test
    void placeOrderThrowsWhenMealNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> burgerStation.placeOrder("Alice", "Unknown Meal"));
    }

    @Test
    void placeOrderAddsOrderToQueue() {
        burgerStation.addMeal(cheeseburger);

        burgerStation.placeOrder("Alice", "Cheeseburger");

        assertEquals(1, burgerStation.orderQueue().size());
    }

    @Test
    void processNextOrderReturnsCompletedOrder() {
        burgerStation.addMeal(cheeseburger);
        burgerStation.placeOrder("Alice", "Cheeseburger");

        Order completed = burgerStation.processNextOrder();

        assertNotNull(completed);
        assertEquals(Order.OrderStatus.COMPLETED, completed.status());
    }

    @Test
    void processNextOrderProcessesInFifoOrder() {
        burgerStation.addMeal(cheeseburger);

        burgerStation.placeOrder("Alice", "Cheeseburger");
        burgerStation.placeOrder("Bob", "Cheeseburger");

        Order first = burgerStation.processNextOrder();

        assertEquals("Alice", first.customerName());
    }

    @Test
    void processNextOrderReturnsNullOnEmptyQueue() {
        assertNull(burgerStation.processNextOrder());
    }

    @Test
    void orderQueueReturnsUnmodifiableList() {
        burgerStation.addMeal(cheeseburger);
        burgerStation.placeOrder("Alice", "Cheeseburger");

        assertThrows(UnsupportedOperationException.class,
                () -> burgerStation.orderQueue().clear());
    }

    @Test
    void burgerStationIsSubclassOfKitchenStation() {
        assertTrue(burgerStation instanceof KitchenStation);
    }

    @Test
    void pizzaStationIsSubclassOfKitchenStation() {
        assertTrue(pizzaStation instanceof KitchenStation);
    }

    @Test
    void getStationName_returnsCorrectName() {
        assertEquals("GrillMaster", burgerStation.getStationName());
        assertEquals("PizzaHub", pizzaStation.getStationName());
    }

    @Test
    void pizzaStation_canProcessOrders() {
        pizzaStation.addMeal(pepperoniPizza);
        pizzaStation.placeOrder("Charlie", "Pepperoni Pizza");

        Order completed = pizzaStation.processNextOrder();

        assertNotNull(completed);
        assertEquals(Order.OrderStatus.COMPLETED, completed.status());
        assertEquals("Charlie", completed.customerName());
    }
}