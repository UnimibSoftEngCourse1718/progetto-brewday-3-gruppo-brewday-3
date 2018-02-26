package group3.brewday.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.brewday.models.Ingredient;
import group3.brewday.models.IngredientUser;
import group3.brewday.models.IngredientUserPk;
import group3.brewday.models.User;
import group3.brewday.models.Ingredient.Type;
import group3.brewday.repositories.IngredientRepository;
import group3.brewday.repositories.IngredientUserRepository;
import group3.brewday.services.IngredientUserService;

@Service
public class IngredientUserServiceImpl implements IngredientUserService {
	
	@Autowired
	IngredientUserRepository ingredientUserRepository;
	
	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	UserServiceImpl userService;
	
	@Override
	public void delete(Long userId, Long ingredientId) {
		IngredientUser ingredientUser = findOne(userId, ingredientId);
		delete(ingredientUser);
	}
	
	@Override
	public void delete(IngredientUser ingredientUser) {
		
		Ingredient ingredient = ingredientUser.getIngredient();
		User user = ingredientUser.getUser();
		Set<IngredientUser> ingredientUsers = ingredient.getIngredientUsers();
		ingredientUsers.remove(ingredientUser);
		ingredient.setIngredientUsers(ingredientUsers);
		ingredientUsers = user.getIngredientUsers();
		ingredientUsers.remove(ingredientUser);
		user.setIngredientUsers(ingredientUsers);
		ingredientUser.setIngredient(ingredient);
		ingredientUser.setUser(user);
		ingredientUserRepository.delete(ingredientUser);	
	}
 	
	@Override
	public void save(String userEmail, IngredientUser ingredientUser) {

		Ingredient formIngredient = ingredientUser.getIngredient();
		String ingredientName = formIngredient.getName();
		Type ingredientType = formIngredient.getType();
		List<Ingredient> ingredients = ingredientRepository.findAll();
		
		Ingredient ingredient = ingredients.stream().filter(i ->  i.getName().equals(ingredientName) && i.getType().equals(ingredientType)).findFirst().orElse(null);
		if(ingredient == null) {
			ingredient = new Ingredient();
			ingredient.setName(ingredientName);
			ingredient.setType(ingredientType);
			ingredientRepository.save(ingredient);
		}
		
		final Long ingredientId = ingredient.getId();		
		IngredientUser existingIngredientUser = findAll().stream().filter(ingredientUserRetrieved -> 
			ingredientUserRetrieved.getIngredientUserPk().getIngredient().getId().equals(ingredientId) &&
			ingredientUserRetrieved.getIngredientUserPk().getUser().getEmail().equals(userEmail)).findFirst().orElse(null);
		
		if(existingIngredientUser == null) {
			User user = userService.findByEmail(userEmail);
			Set<IngredientUser> ingredientUsers = ingredient.getIngredientUsers();
			ingredientUsers.add(ingredientUser);
			ingredient.setIngredientUsers(ingredientUsers);
			ingredientUsers = user.getIngredientUsers();
			ingredientUsers.add(ingredientUser);
			user.setIngredientUsers(ingredientUsers);
			ingredientUser.setUser(user);
			ingredientUser.setIngredient(ingredient);
			ingredientUserRepository.save(ingredientUser);
		}
	}
	
	@Override
	public void save(IngredientUser ingredientUser) {
		ingredientUserRepository.save(ingredientUser);
	}
	
	@Override
	public IngredientUser findOne(String userEmail, Long ingredientId) {
		return ingredientUserRepository.findOne(new IngredientUserPk(ingredientRepository.findOne(ingredientId), userService.findByEmail(userEmail)));
	}
	
	@Override
	public IngredientUser findOne(Long userId, Long ingredientId) {
		return ingredientUserRepository.findOne(new IngredientUserPk(ingredientRepository.findOne(ingredientId), userService.findOne(userId)));
	}
	
	@Override
	public List<IngredientUser> findAll() {
		return ingredientUserRepository.findAll();
	}
	
	public List<IngredientUser> findByUser(String userEmail) {
		List<IngredientUser> ingredientUsers = findAll();
		return ingredientUsers.stream().filter(ingredientUser -> ingredientUser.getUser().getId().equals((userService.findByEmail(userEmail).getId()))).collect(Collectors.toList());
	}
}
