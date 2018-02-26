package group3.brewday.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import group3.brewday.models.IngredientUser;
import group3.brewday.repositories.IngredientRepository;
import group3.brewday.services.impl.IngredientUserServiceImpl;



@Controller
public class IngredientFormController {

	@Autowired
	IngredientUserServiceImpl ingredientUserService;
	
	@Autowired
	IngredientRepository ingredientRepository;

	@GetMapping("/ingredientform")
	public String newIngredient(Model model, Authentication auth){
		model.addAttribute("ingredientUser", new IngredientUser());
		model.addAttribute("username",auth.getName());
		return "ingredientform";
	}

	@PostMapping("/ingredient")
	@Transactional
	public String saveIngredient(IngredientUser ingredientUser, Authentication auth){
		ingredientUserService.save(auth.getName(), ingredientUser);
		return "redirect:/ingredients";
	}
	
	@GetMapping("/ingredientuserdelete/{userid}/{ingredientid}")
	public String ingredientDelete(@PathVariable("userid") Long userId, @PathVariable("ingredientid") Long ingredientId, Model model) {
		ingredientUserService.delete(userId, ingredientId);	
		return "redirect:/ingredients";
	}

	@GetMapping("/ingredientuserupdate/{userid}/{ingredientid}")
	public String ingredientUpdate(@PathVariable("userid") Long userId, @PathVariable("ingredientid") Long ingredientId, Model model) {
		model.addAttribute("ingredientUser", ingredientUserService.findOne(ingredientId, userId));
		return "ingredientuserupdate";
	}
	
	@PutMapping("/ingredientuserupdate")
	public String ingredientUpdate(IngredientUser ingredientUser) {
		ingredientUserService.save(ingredientUser);		
		return "redirect:/ingredients";
	}
	
	@GetMapping(value = "/ingredients")
	public String list(Model model, Authentication auth){
		model.addAttribute("ingredientUsers", ingredientUserService.findByUser(auth.getName()));
		return "ingredients";
	}	

}


