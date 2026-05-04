package com.fastfood;

import com.fastfood.model.Ingredient;
import com.fastfood.model.Meal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    private Meal buildCheeseburger() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Bun", 2));
        ingredients.add(new Ingredient("Patty", 1));
        ingredients.add(new Ingredient("Cheese", 1));

        return new Meal("Cheeseburger", ingredients);
    }

    @Test
    void constructor_setsNameAndIngredients() {
        Meal meal = buildCheeseburger();

        assertEquals("Cheeseburger", meal.name());
        assertEquals(3, meal.ingredients().size());
    }

    @Test
    void ingredients_returnsDefensiveCopy() {
        Meal meal = buildCheeseburger();

        List<Ingredient> copy = meal.ingredients();
        copy.add(new Ingredient("Tomato", 1));

        assertEquals(3, meal.ingredients().size());
    }

    @Test
    void addIngredient_increasesIngredientCount() {
        Meal meal = buildCheeseburger();

        meal.addIngredient(new Ingredient("Lettuce", 1));

        assertEquals(4, meal.ingredients().size());
    }

    @Test
    void toString_containsMealName() {
        Meal meal = buildCheeseburger();

        assertTrue(meal.toString().contains("Cheeseburger"));
    }

    @Test
    void toString_containsIngredientDetails() {
        Meal meal = buildCheeseburger();

        String result = meal.toString();

        assertTrue(result.contains("Bun"));
        assertTrue(result.contains("Patty"));
        assertTrue(result.contains("Cheese"));
    }
}