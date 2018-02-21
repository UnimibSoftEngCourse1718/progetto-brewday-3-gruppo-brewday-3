package group3.brewday.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.brewday.models.Ingredient;
import group3.brewday.models.Recipe;
import group3.brewday.repositories.RecipeRepository;



@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public void setIngredientRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> listAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findOne(id);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
    
    @Override
    public void deleteRecipe(Long id) {
         recipeRepository.delete(id);
         return;
    }
    
    @Override
    public void addRecipeIngredient(Long id, Ingredient ingredient) {
    	Recipe recipe = recipeRepository.findOne(id);
    	List<Ingredient> ingredients = recipe.getIngredients();
    	ingredients.add(ingredient);
    	recipe.setIngredients(ingredients);
    	recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipeIngredient(Long id, Long ingredientId) {
    	Recipe recipe = recipeRepository.findOne(id);
    	List<Ingredient> ingredients = recipe.getIngredients().stream().filter(ingredient -> !ingredientId.equals(ingredient.getId())).collect(Collectors.toList());
    	recipe.setIngredients(ingredients);
    	recipeRepository.save(recipe);
    }
}