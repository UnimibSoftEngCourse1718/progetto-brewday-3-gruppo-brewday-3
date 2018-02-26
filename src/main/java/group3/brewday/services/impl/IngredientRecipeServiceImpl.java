package group3.brewday.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.brewday.models.Ingredient;
import group3.brewday.models.IngredientRecipe;
import group3.brewday.models.IngredientRecipePk;
import group3.brewday.models.Recipe;
import group3.brewday.models.Ingredient.Type;
import group3.brewday.repositories.IngredientRecipeRepository;
import group3.brewday.repositories.IngredientRepository;
import group3.brewday.services.IngredientRecipeService;

@Service
public class IngredientRecipeServiceImpl implements IngredientRecipeService {
	
	@Autowired
	IngredientRecipeRepository ingredientRecipeRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	RecipeServiceImpl recipeService;
	
	@Override
	public void delete(Long recipeId, Long ingredientId) {
		
		IngredientRecipe ingredientRecipe = findOne(recipeId, ingredientId);
		delete(ingredientRecipe);
	}
	
	@Override
	public void delete(IngredientRecipe ingredientRecipe) {
		
		Ingredient ingredient = ingredientRecipe.getIngredient();
		Recipe recipe = ingredientRecipe.getRecipe();
		ingredient.setIngredientRecipes(null);
		recipe.setIngredientRecipes(null);
		ingredientRecipe.setIngredient(ingredient);
		ingredientRecipe.setRecipe(recipe);
		ingredientRecipeRepository.delete(ingredientRecipe);	
	}
	
	public void delete(Set<IngredientRecipe> ingredientRecipes) {
		ingredientRecipes.forEach(ingredientRecipe -> delete(ingredientRecipe));
	}
 	
	@Override
	public void save(Long id, IngredientRecipe ingredientRecipe) {
		Recipe recipe = recipeService.findOne(id);
		String ingredientName = ingredientRecipe.getIngredient().getName();
		Type ingredientType = ingredientRecipe.getIngredient().getType();
		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		Ingredient ingredient = ingredients.stream().filter(i ->  i.getName().equals(ingredientName) && i.getType().equals(ingredientType)).findFirst().orElse(null);
		if(ingredient == null) {
			ingredient = new Ingredient();
			ingredient.setName(ingredientName);
			ingredient.setType(ingredientType);
			ingredientRepository.save(ingredient);
		}
		
		final Long ingredientId = ingredient.getId();		
		IngredientRecipe existingIngredientRecipe = findAll().stream().filter(ingredientRecipeRetrieved -> 
			ingredientRecipeRetrieved.getIngredientRecipePk().getIngredient().getId().equals(ingredientId) &&
			ingredientRecipeRetrieved.getIngredientRecipePk().getRecipe().getId().equals(id)).findFirst().orElse(null);
		
		if(existingIngredientRecipe == null) {
		
			Set<IngredientRecipe> ingredientRecipes = ingredient.getIngredientRecipes();
			ingredientRecipes.add(ingredientRecipe);
			ingredient.setIngredientRecipes(ingredientRecipes);
			ingredientRecipes = recipe.getIngredientRecipes();
			ingredientRecipes.add(ingredientRecipe);
			recipe.setIngredientRecipes(ingredientRecipes);
			ingredientRecipe.setRecipe(recipe);
			ingredientRecipe.setIngredient(ingredient);
			ingredientRecipeRepository.save(ingredientRecipe);
		}
	}
	
	@Override
	public void save(IngredientRecipe ingredientRecipe) {
		ingredientRecipeRepository.save(ingredientRecipe);
	}
	
	@Override
	public IngredientRecipe findOne(Long recipeId, Long ingredientId) {
		return ingredientRecipeRepository.findOne(new IngredientRecipePk(ingredientRepository.findOne(ingredientId), recipeService.findOne(recipeId)));
	}
	
	@Override
	public List<IngredientRecipe> findAll() {
		return ingredientRecipeRepository.findAll();
	}
}
