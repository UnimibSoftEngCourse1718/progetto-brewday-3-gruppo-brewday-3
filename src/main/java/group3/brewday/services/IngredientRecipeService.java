package group3.brewday.services;

import java.util.List;

import group3.brewday.models.IngredientRecipe;

public interface IngredientRecipeService {

	void delete(IngredientRecipe ingredientRecipe);
	
	void delete(Long recipeId, Long ingredientId);

	void save(Long id, IngredientRecipe ingredientRecipe);

	void save(IngredientRecipe ingredientRecipe);

	IngredientRecipe findOne(Long recipeId, Long ingredientId);

	List<IngredientRecipe> findAll();

}
