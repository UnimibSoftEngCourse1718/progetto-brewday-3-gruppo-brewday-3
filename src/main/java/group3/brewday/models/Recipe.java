package group3.brewday.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe implements Serializable {

	private static final long serialVersionUID = -3552125788239752821L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@ElementCollection 
	private  Set<IngredientRecipe> ingredientRecipe;

	private Double yield;
	private String description;
	private String emailUser;

	public Recipe() {
		super();
	}

	public Recipe(Long id, String name, Double yield, String description, String emailUser) {
		super();
		this.id = id;
		this.name = name;
		this.yield = yield;
		this.description = description;
		this.emailUser = emailUser;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<IngredientRecipe> getIngredientRecipes() {
		return ingredientRecipe == null ? new HashSet<IngredientRecipe>() : ingredientRecipe;
	}

	public void setIngredientRecipes(Set<IngredientRecipe> ingredientRecipe) {
		this.ingredientRecipe = ingredientRecipe;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

}	