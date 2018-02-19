package group3.brewday.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private  Ingredient[] ingredients;
	private Double yield;
	private String emailUser;
	
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
	
	public Ingredient[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}

	public Double getYield() {
		return yield;
	}
	
	public void setYield(Double yield) {
		this.yield = yield;
	}
	
	public String getEmailUser() {
		return emailUser;
	}
	
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
	
	
	public Recipe(Long id, String name, Ingredient[] ingredients, Double yield, String emailUser) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.yield = yield;
		this.emailUser = emailUser;
	}

	public Recipe() {
		super();
	}
	
	
	
}	