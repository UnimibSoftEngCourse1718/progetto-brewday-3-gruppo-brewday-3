package group3.brewday.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import group3.brewday.models.Ingredient;
import group3.brewday.models.IngredientUser;
import group3.brewday.models.User;
import group3.brewday.repositories.IngredientRepository;
import group3.brewday.repositories.IngredientUserRepository;
import group3.brewday.services.UserService;



@Controller
public class IngredientFormController {

	@Autowired
	IngredientUserRepository ingredientUserRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	UserService userService;


	@GetMapping("/ingredientform")
	public String newIngredient(Model model, Authentication auth){
		model.addAttribute("ingredientUser", new IngredientUser());
		model.addAttribute("username",auth.getName());
		return "ingredientform";
	}

	@PostMapping("/ingredient")
	@Transactional
	public String saveIngredient(IngredientUser ingredientUser, Authentication auth){
		
		Ingredient formIngredient = ingredientUser.getIngredient();
		List<Ingredient> ingredients = ingredientRepository.findAll();
		Ingredient ingredient = ingredients.stream().filter(i ->  i.getName().equals(formIngredient.getName()) && i.getType().equals(formIngredient.getType())).findFirst().orElse(null);
		if(ingredient == null) {
			ingredient = new Ingredient();
			ingredient.setName(formIngredient.getName());
			ingredient.setType(formIngredient.getType());
			ingredientRepository.save(ingredient);
		}
		User user = userService.findByEmail(auth.getName());
		ingredientUser.setUser(user);
		ingredientUser.setIngredient(ingredient);
		ingredientUserRepository.save(ingredientUser);
		
		return "redirect:/ingredients";
	}

	@GetMapping(value = "/ingredients")
	public String list(Model model, Authentication auth){
		List<IngredientUser> ingredientUsers = ingredientUserRepository.findAll();
		model.addAttribute("ingredientUsers", ingredientUsers.stream().filter(ingredientUser -> ingredientUser.getUser().getId().equals((userService.findByEmail(auth.getName()).getId()))).collect(Collectors.toList()));
		model.addAttribute("ingredientUserEmpty", new IngredientUser());
		return "ingredients";
	}	

//	@PostMapping("ingredient/delete")
//	public String deleteIngredient(IngredientUser ingredientUser, Authentication auth) {
//		ingredientUser.setUser(userService.findByEmail(auth.getName()));
//		ingredientUserRepository.delete(ingredientUser);
//		return "redirect:/ingredients";
//	}

}


