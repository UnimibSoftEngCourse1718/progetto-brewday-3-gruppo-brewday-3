package group3.brewday.services.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.brewday.models.IngredientRecipe;
import group3.brewday.models.Recipe;
import group3.brewday.repositories.RecipeRepository;
import group3.brewday.services.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	IngredientRecipeServiceImpl ingredientRecipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
 
	@Override
	@Transactional
	public void delete(Long recipeId) {
		
		Recipe recipe = recipeRepository.findOne(recipeId);
		Set<IngredientRecipe> ingredientRecipes = recipe.getIngredientRecipes();
		ingredientRecipeService.delete(ingredientRecipes);
		recipeRepository.delete(recipe);
	}
	
	@Override
	public void save(Recipe recipe) {
		recipeRepository.save(recipe);
	}
	
	@Override
	public Recipe findOne(Long id) {
		return recipeRepository.findOne(id);
	}
	
	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}
}
