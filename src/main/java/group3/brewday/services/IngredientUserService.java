package group3.brewday.services;

import java.util.List;

import group3.brewday.models.IngredientUser;

public interface IngredientUserService {

	void delete(IngredientUser ingredientUser);
	
	void delete(Long userId, Long ingredientId);

	void save(String userEmail, IngredientUser ingredientUser);

	void save(IngredientUser ingredientUser);

	IngredientUser findOne(String userEmail, Long ingredientId);

	IngredientUser findOne(Long userId, Long ingredientId);
	
	List<IngredientUser> findAll();

}
