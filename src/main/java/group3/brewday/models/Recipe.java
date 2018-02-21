package group3.brewday.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="REC_ID")
	private Long id;

	private String name;

	@ElementCollection 
	@CollectionTable(name="ingredients")	
	private  List<Ingredient> ingredients;

	private Double yield;
	private String description;
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

	public List<Ingredient> getIngredients() {
		return ingredients == null ? new ArrayList<Ingredient>() : ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

	public Recipe() {
		super();
	}

	public Recipe(Long id, String name, List<Ingredient> ingredients, Double yield, String description,
			String emailUser) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.yield = yield;
		this.description = description;
		this.emailUser = emailUser;
	}

	public Recipe(List<Ingredient> ingredients) {
		super();
		this.ingredients = ingredients;
	}


}	