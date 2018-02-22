package group3.brewday.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import group3.brewday.models.IngredientUser;
import group3.brewday.models.IngredientUserPk;

public interface IngredientUserRepository extends JpaRepository<IngredientUser, IngredientUserPk>{
	
}
