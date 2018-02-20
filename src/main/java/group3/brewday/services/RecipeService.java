package group3.brewday.services;

import java.util.List;

import group3.brewday.models.Recipe;

public interface RecipeService {

	List<Recipe> listAllRecipes();

	Recipe getRecipeById(Long id);

	Recipe saveRecipe(Recipe recipe); 

	void deleteRecipe(Long id);

}