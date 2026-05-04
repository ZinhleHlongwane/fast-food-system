cat > README.md <<'EOF'
# Fast Food Order Management System

A Java OOP project for practising **Encapsulation, Composition, Inheritance, Lists, HashMaps, Unit Testing, and UML design**.

This project simulates a fast food restaurant system where meals are stored on a menu, customers place orders, and kitchen stations process those orders in queue order.

---

# 1. Project Goal

The goal of this project is to build a system similar to the Coffee Machine assessment, but with a new theme.

Instead of:

- Coffee recipes
- Coffee machines
- Coffee orders

This project uses:

- Meals
- Kitchen stations
- Customer food orders

---

# 2. What This Project Teaches

By completing this project, I should understand:

## Encapsulation

Fields must be private.

Example:

private String name;

Outside classes should not directly change fields.

---

## Composition

Composition means one class has another class.

Example:

private List<Ingredient> ingredients;

A Meal has many Ingredient objects.

---

## Inheritance

Inheritance means one class is a type of another class.

Example:

public class BurgerStation extends KitchenStation

---

## Lists

A List stores multiple objects in order.

Example:

List<Order> orderQueue;

FIFO = First In, First Out

---

## HashMaps

A Map stores key-value pairs.

Example:

Map<String, Meal> meals;

"Cheeseburger" -> Cheeseburger Meal object

Use:
- put() to store
- get() to retrieve

---

# 3. Project Structure

fast-food-system/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/fastfood/
    │   ├── Main.java
    │   ├── model/
    │   │   ├── Ingredient.java
    │   │   ├── Meal.java
    │   │   └── Order.java
    │   └── service/
    │       ├── KitchenStation.java
    │       ├── BurgerStation.java
    │       └── PizzaStation.java
    └── test/java/com/fastfood/
        ├── IngredientTest.java
        ├── MealTest.java
        ├── OrderTest.java
        └── KitchenStationTest.java

---

# 4. Package Rules

Main.java:
package com.fastfood;

Model classes:
package com.fastfood.model;

Service classes:
package com.fastfood.service;

---

# 5. Step 1 — Ingredient

File:
src/main/java/com/fastfood/model/Ingredient.java

Purpose:
Represents one ingredient in a meal.

Examples:
- Bun
- Patty
- Cheese

Fields:
- private String name;
- private int quantity;

Constructor:
Ingredient(String name, int quantity)

Rules:
- Store values
- Reject negative quantity

Methods:
- name()
- quantity()
- updateQuantity()
- toString()

Validation:
Negative quantity throws IllegalArgumentException

---

# 6. Step 2 — Meal

File:
src/main/java/com/fastfood/model/Meal.java

Purpose:
Represents one meal on the menu.

Fields:
- private String name;
- private List<Ingredient> ingredients;

Constructor:
Meal(String name, List<Ingredient> ingredients)

Important:
Use defensive copy:

this.ingredients = new ArrayList<>(ingredients);

Do NOT store original list directly.

Methods:
- name()
- ingredients()
- addIngredient()
- toString()

toString() should:
1. Start with meal name
2. Loop through ingredients
3. Add each ingredient on a new line

---

# 7. Step 3 — Order

File:
src/main/java/com/fastfood/model/Order.java

Purpose:
Represents a customer order.

Fields:
- orderId
- customerName
- meal
- status

Enum:
PENDING
PREPARING
COMPLETED
CANCELLED

Constructor:
Order(int orderId, String customerName, Meal meal)

Default status:
PENDING

Methods:
- orderId()
- customerName()
- customer()
- meal()
- status()
- updateStatus()
- toString()

---

# 8. Step 4 — KitchenStation

File:
src/main/java/com/fastfood/service/KitchenStation.java

Purpose:
Abstract parent class for all kitchen stations.

Fields:
- stationName
- meals
- orderQueue
- orderCounter

Constructor:
Initialize:
meals = new HashMap<>();
orderQueue = new ArrayList<>();
orderCounter = 0;

Methods:
- addMeal()
- getMeal()
- getAllMeals()
- placeOrder()
- processNextOrder()
- orderQueue()
- stationName()

Abstract Method:
protected abstract void prepare(Order order);

---

# 9. HashMap Logic

Store meal:
meals.put(meal.name(), meal);

Retrieve meal:
meals.get("Cheeseburger");

---

# 10. placeOrder Logic

1. Find meal
2. If null -> throw IllegalArgumentException
3. Increment orderCounter
4. Create Order
5. Add to queue
6. Return order

---

# 11. processNextOrder Logic

1. Loop through orderQueue
2. Find first PENDING
3. Set PREPARING
4. Call prepare(order)
5. Set COMPLETED
6. Return order
7. Return null if no pending orders

---

# 12. BurgerStation

Extends KitchenStation

Preparation:
[stationName] preparing [mealName] for [customerName] using grilled burger preparation.

---

# 13. PizzaStation

Extends KitchenStation

Preparation:
[stationName] preparing [mealName] for [customerName] using baked oven preparation.

---

# 14. Development Order

1. Ingredient
2. IngredientTest
3. Meal
4. MealTest
5. Order
6. OrderTest
7. KitchenStation
8. BurgerStation
9. PizzaStation
10. KitchenStationTest

---

# 15. Common Errors

Package does not exist:
Check folder + package line

Cannot find symbol:
Check class name + imports

Method does not exist:
Check spelling + exact test method names

---

# 16. Run Commands

Compile:
mvn clean compile

Test:
mvn clean test

---

# 17. Git Commit Plan

Initialize:
git commit -m "Initialize fast food system"

Ingredient:
git commit -m "Implement Ingredient model"

Meal:
git commit -m "Implement Meal model"

Order:
git commit -m "Implement Order model"

Kitchen:
git commit -m "Implement kitchen station system"

Final:
git commit -m "Complete fast food order management system"

---

# 18. UML Requirements

Classes:
- Ingredient
- Meal
- Order
- KitchenStation
- BurgerStation
- PizzaStation

Relationships:
- Meal has Ingredients
- Order has Meal
- KitchenStation has Meals
- KitchenStation has Orders
- BurgerStation extends KitchenStation
- PizzaStation extends KitchenStation

---

# 19. Final Problem Statement

Restaurants need a system to manage meals and customer orders efficiently. This project enables staff to store menu items, place customer orders, and process them in the correct order through specialized kitchen stations.

---

# 20. Final Checklist

[ ] Compiles
[ ] Tests pass
[ ] Fields private
[ ] Defensive copies
[ ] HashMap works
[ ] FIFO queue works
[ ] Inheritance works
[ ] UML complete
[ ] GitHub ready
EOF