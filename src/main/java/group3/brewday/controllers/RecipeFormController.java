
package group3.brewday.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import group3.brewday.models.IngredientRecipe;
import group3.brewday.models.Recipe;
import group3.brewday.repositories.IngredientRepository;
import group3.brewday.services.impl.IngredientRecipeServiceImpl;
import group3.brewday.services.impl.RecipeServiceImpl;

@Controller
public class RecipeFormController {

	@Autowired
	RecipeServiceImpl recipeService;
	
	@Autowired
	IngredientRecipeServiceImpl ingredientRecipeService;
		
	@Autowired
	IngredientRepository ingredientRepository;

	@GetMapping(value = "/recipes")
	public String list(Model model, Authentication auth){
		List<Recipe> recipes = recipeService.findAll();
		model.addAttribute("recipes", recipes.stream().filter(recipe -> auth.getName().equals(recipe.getEmailUser())).collect(Collectors.toList()));
		return "recipes";
	}
	
	@GetMapping("/addingredient/{id}")
	public String newIngredient(@PathVariable Long id, Model model, Authentication auth){
		model.addAttribute("ingredientRecipe", new IngredientRecipe());
		model.addAttribute("username",auth.getName());
		model.addAttribute("recipeid", id);
		return "addingform";
	}

	@PostMapping("/addingredient/{id}")
	@Transactional
	public String addIngRecipe(@PathVariable Long id, IngredientRecipe ingredientRecipe, Authentication auth) {
		ingredientRecipeService.save(id, ingredientRecipe);
		return "redirect:/recipe/" + id;
	}	

	@GetMapping("/ingredientrecipedelete/{recipeid}/{ingredientid}")
	public String ingredientDelete(@PathVariable("recipeid") Long recipeId, @PathVariable("ingredientid") Long ingredientId, Model model) {
		ingredientRecipeService.delete(recipeId, ingredientId);	
		return "redirect:/recipe/" + recipeId;
	}
	
	@GetMapping("/ingredientrecipeupdate/{recipeid}/{ingredientid}")
	public String ingredientUpdate(@PathVariable("recipeid") Long recipeId, @PathVariable("ingredientid") Long ingredientId, Model model) {
		IngredientRecipe ingredientRecipeRetrieved = ingredientRecipeService.findOne(recipeId, ingredientId);
		model.addAttribute("ingredientRecipe", ingredientRecipeRetrieved);
		return "ingredientrecipeupdate";
	}
	
	@PutMapping("/ingredientrecipeupdate")
	public String ingredientUpdate(IngredientRecipe ingredientRecipe) {
		ingredientRecipeService.save(ingredientRecipe);		
		return "redirect:/recipe/" + ingredientRecipe.getRecipe().getId();
	}
	
	@DeleteMapping("recipe/delete/{id}")
	public String deleteRecipe(@PathVariable Long id) {
		recipeService.delete(id);
		return "redirect:/recipes";
	}

	@PostMapping("/recipe")
	public String saveRecipe(Recipe recipe, Authentication auth){
		recipe.setEmailUser(auth.getName());
		recipeService.save(recipe);
		return "redirect:/recipes";
	}

	@GetMapping("/recipeform")
	public String newRecipeIng(Model model, Authentication auth){
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("username",auth.getName());
		return "recipeform";
	}

	@GetMapping("recipe/{id}")
	public String showRecipe(@PathVariable Long id, Model model){
		model.addAttribute("recipe", recipeService.findOne(id));
		return "recipeshow";
	}

}