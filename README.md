# Fast Food Order Management System

## Overview

This is a Java OOP project that simulates a fast food restaurant order system.

The system allows a kitchen station to:

- Store meals on a menu
- Store ingredients for each meal
- Place customer orders
- Process orders in queue order
- Use inheritance for different kitchen station types

This project is inspired by a Coffee Machine OOP assessment and is meant to help practise:

- Encapsulation
- Inheritance
- Composition
- Lists
- HashMaps
- Unit testing
- UML class design

---

## Project Structure

```text
fast-food-system/
├── pom.xml
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