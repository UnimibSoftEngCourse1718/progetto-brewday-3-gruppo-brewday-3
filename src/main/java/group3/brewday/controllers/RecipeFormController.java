
package group3.brewday.controllers;

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

import group3.brewday.models.Ingredient;
import group3.brewday.models.Recipe;
import group3.brewday.services.IngredientService;
import group3.brewday.services.RecipeService;

@Controller
public class RecipeFormController {

	@Autowired
	RecipeService recipeService;
	@Autowired
	IngredientService ingredientService1;


	@GetMapping(value = "/recipes")
	public String list(Model model, Authentication auth){
		List<Recipe> allRecipes = recipeService.listAllRecipes();
		model.addAttribute("recipes", allRecipes.stream().filter(recipe -> auth.getName().equals(recipe.getEmailUser())).collect(Collectors.toList()));
		return "recipes";
	}

	@GetMapping("/recipeinglist")
	public String newRecipe(Model model, Authentication auth){
		model.addAttribute("addingredients", ingredientService1.listAllIngredients());
		return "recipeinglist";
	}

	@GetMapping("/addingform")
	public String newIngredient(Model model, Authentication auth){
		model.addAttribute("addingredient", new Ingredient());
		model.addAttribute("username",auth.getName());
		return "addingform";
	}

	@DeleteMapping("addingredient/delete/{id}")
	public String delIngredient(@PathVariable Long id){
		recipeService.deleteRecipe(id);;
		return "redirect:/recipeinglist";
	}

	
	@PostMapping(value = "addingredient")
	public String addIngRecipe(Ingredient addingredient, Authentication auth){
		addingredient.setEmailUser(auth.getName());
		ingredientService1.saveIngredient(addingredient);			
		return "redirect:/recipeinglist";
	}	


	@DeleteMapping("recipe/delete/{id}")
	public String recipeIngredient(@PathVariable Long id){
		recipeService.deleteRecipe(id);;
		return "redirect:/recipes";
	}

	@PostMapping(value = "recipe")
	public String saveRecipe(Recipe recipe, Authentication auth){

		recipe.setEmailUser(auth.getName());
		recipeService.saveRecipe(recipe);

		return "redirect:/recipes";
	}

	@GetMapping("/recipeform")
	public String newRecipeIng(Model model, Authentication auth){
		Ingredient[] ingredients = ingredientService1.listAllIngredients().toArray(new Ingredient[ingredientService1.listAllIngredients().size()]);
		model.addAttribute("recipe", new Recipe(ingredients));
		ingredientService1.deleteAll();
		model.addAttribute("username",auth.getName());
		return "recipeform";
	}



}