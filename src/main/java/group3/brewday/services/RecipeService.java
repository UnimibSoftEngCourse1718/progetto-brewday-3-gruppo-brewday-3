package group3.brewday.services;

import java.util.List;

import group3.brewday.models.Recipe;

public interface RecipeService {

	void delete(Long recipeId);

	void save(Recipe recipe);

	Recipe findOne(Long id);

	List<Recipe> findAll();

}
