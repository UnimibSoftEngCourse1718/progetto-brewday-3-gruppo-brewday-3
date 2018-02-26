package group3.brewday.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import group3.brewday.models.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
