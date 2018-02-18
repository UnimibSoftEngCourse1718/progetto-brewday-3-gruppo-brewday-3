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
import group3.brewday.services.IngredientService;

@Controller
public class IngredientFormController {
   
	@Autowired
	IngredientService ingredientService;
	
    @GetMapping("/ingredientform")
    public String newIngredient(Model model, Authentication auth){
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("username",auth.getName());
        return "ingredientform";
    }
	
	@PostMapping(value = "ingredient")
    public String saveIngredient(Ingredient ingredient, Authentication auth){

		ingredient.setEmailUser(auth.getName());
        ingredientService.saveIngredient(ingredient);

        return "redirect:/ingredients";
    }
	
    @GetMapping(value = "/ingredients")
    public String list(Model model, Authentication auth){
    	List<Ingredient> allIngredients = ingredientService.listAllIngredients();
        model.addAttribute("ingredients", allIngredients.stream().filter(ingredient -> auth.getName().equals(ingredient.getEmailUser())).collect(Collectors.toList()));
        return "ingredients";
    }	
	
    @GetMapping("ingredient/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredientService.getIngredientById(id));
        return "ingredientform";
    }
    
    @DeleteMapping("ingredient/delete/{id}")
    public String deleteIngredient(@PathVariable Long id){
            ingredientService.deleteIngredient(id);
            return "redirect:/ingredients";
    }
    
}

    
