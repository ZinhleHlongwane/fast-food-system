package com.fastfood.model;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private String name;
    private List<Ingredient> ingredients;
    
    public Meal(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
    }

    public String name() {
        return this.name;
    }

    public List<Ingredient> ingredients() {
        return new ArrayList<>(ingredients);
    }

    public void addIngredient(Ingredient ingredients) {
        this.ingredients.add(ingredients);
    }

    @Override
    public String toString() {
        String result = name();

        for (int i = 0; i < ingredients.size(); i++) {
            result = result + "\n" + ingredients.get(i);
        }

        return result;
    }
}