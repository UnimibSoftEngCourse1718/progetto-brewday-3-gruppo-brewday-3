package group3.brewday.services;

import java.util.List;

import group3.brewday.models.Ingredient;

public interface IngredientService {

	List<Ingredient> listAllIngredients();

	Ingredient getIngredientById(Long id);

	Ingredient saveIngredient(Ingredient ingredient); 

	void deleteIngredient(Long id);

	void deleteAll();

}