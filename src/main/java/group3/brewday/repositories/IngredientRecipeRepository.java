package group3.brewday.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import group3.brewday.models.IngredientRecipe;
import group3.brewday.models.IngredientRecipePk;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, IngredientRecipePk>{
}
