package group3.brewday.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import group3.brewday.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
}
