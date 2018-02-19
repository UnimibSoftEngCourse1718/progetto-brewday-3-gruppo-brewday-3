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
import group3.brewday.services.RecipeService;

@Controller
public class RecipeFormController {
   
	@Autowired
	RecipeService recipeService;
	Ingredient[] ingredient = new Ingredient[10];
	
    @GetMapping("/ingredientform")
    public String newIngredient(Model model, Authentication auth){
        model.addAttribute("ingredient", new Recipe());
        model.addAttribute("username",auth.getName());
        return "ingredientform";
    }
	
	@PostMapping(value = "recipe")
    public String saveRecipe(Recipe recipe, Authentication auth){

		recipe.setEmailUser(auth.getName());
        recipeService.saveRecipe(recipe);

        return "redirect:/ingredients";
    }
	
    @GetMapping(value = "/ingredients")
    public String list(Model model, Authentication auth){
    	List<Recipe> allRecipes = recipeService.listAllRecipes();
        model.addAttribute("recipes", allRecipes.stream().filter(recipe -> auth.getName().equals(recipe.getEmailUser())).collect(Collectors.toList()));
        return "recipes";
    }	
	
    @GetMapping("ingredient/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("ingredient", recipeService.getRecipeById(id));
        return "ingredientform";
    }
    
    @DeleteMapping("ingredient/delete/{id}")
    public String deleteIngredient(@PathVariable Long id){
            recipeService.deleteRecipe(id);;
            return "redirect:/ingredients";
    }
    
}

    
