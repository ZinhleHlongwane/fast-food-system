package com.fastfood;

import com.fastfood.model.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void constructor_setsNameAndQuantity() {
        Ingredient ingredient = new Ingredient("Cheese", 2);

        assertEquals("Cheese", ingredient.name());
        assertEquals(2, ingredient.quantity());
    }

    @Test
    void updateQuantity_updatesValue() {
        Ingredient ingredient = new Ingredient("Patty", 1);

        ingredient.updateQuantity(3);

        assertEquals(3, ingredient.quantity());
    }

    @Test
    void updateQuantity_allowsZero() {
        Ingredient ingredient = new Ingredient("Sauce", 1);

        ingredient.updateQuantity(0);

        assertEquals(0, ingredient.quantity());
    }

    @Test
    void updateQuantity_throwsOnNegativeValue() {
        Ingredient ingredient = new Ingredient("Bun", 2);

        assertThrows(IllegalArgumentException.class,
                () -> ingredient.updateQuantity(-1));
    }

    @Test
    void toString_containsNameAndQuantity() {
        Ingredient ingredient = new Ingredient("Cheese", 2);

        String result = ingredient.toString();

        assertTrue(result.contains("Cheese"));
        assertTrue(result.contains("2"));
    }
}