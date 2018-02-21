
package group3.brewday.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group3.brewday.models.Ingredient;
import group3.brewday.models.Recipe;
import group3.brewday.services.RecipeService;

@Controller
public class RecipeFormController {

	@Autowired
	RecipeService recipeService;

	@GetMapping(value = "/recipes")
	public String list(Model model, Authentication auth){
		List<Recipe> allRecipes = recipeService.listAllRecipes();
		model.addAttribute("recipes", allRecipes.stream().filter(recipe -> auth.getName().equals(recipe.getEmailUser())).collect(Collectors.toList()));
		return "recipes";
	}
	
	@GetMapping("/addingredient/{id}")
	public String newIngredient(@PathVariable Long id, Model model, Authentication auth){
		model.addAttribute("ingredient", new Ingredient());
		model.addAttribute("username",auth.getName());
		model.addAttribute("recipeid", id);
		return "addingform";
	}

	@DeleteMapping("/addingredient/delete/{id}/{ingredientId}")
	public String delIngredient(@PathVariable Long id, @PathVariable Long ingredientId) {
		recipeService.deleteRecipeIngredient(id, ingredientId);
		return "redirect:/recipes";
	}

	@PostMapping("/addingredient/{id}")
	public String addIngRecipe(@PathVariable Long id, Ingredient ingredient, Authentication auth) {
		ingredient.setEmailUser(auth.getName());
		ingredient.setId(1l);
		recipeService.addRecipeIngredient(id, ingredient);
		return "redirect:/recipes";
	}	


	@DeleteMapping("recipe/delete/{id}")
	public String deleteRecipe(@PathVariable Long id){
		recipeService.deleteRecipe(id);
		return "redirect:/recipes";
	}

	@PostMapping("/recipe")
	public String saveRecipe(Recipe recipe, Authentication auth){
		recipe.setEmailUser(auth.getName());
		recipeService.saveRecipe(recipe);
		System.out.println(recipe.getIngredients().isEmpty());

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
		
		model.addAttribute("recipe", recipeService.getRecipeById(id));
		return "recipeshow";
	}

}