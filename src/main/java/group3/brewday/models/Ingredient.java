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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ingredient", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "type"}))
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 3086561547558706906L;

	public Ingredient(Long id, Type type, String name) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
	}

	public Ingredient() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public static enum Type {malt, hop, yeast, sugar, additive};
	private Type type;
	private String name;

	@ElementCollection
    private Set<IngredientUser> ingredientUser;
	
//	@ElementCollection
//    private Set<IngredientRecipe> ingredientRecipe;
	
	public Type getType() {
		return type;
	} 
	
	public void setType(Type type) {
		this.type = type;
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
	
	@OneToMany(mappedBy = "ingredient", cascade=CascadeType.ALL)
	public Set<IngredientUser> getIngredientUser() {
		return ingredientUser == null ? new HashSet<IngredientUser>() : ingredientUser;
	}

	public void setIngredientUser(Set<IngredientUser> ingredientUser) {
		this.ingredientUser = ingredientUser;
	}

//	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)	
//	public Set<IngredientRecipe> getIngredientRecipe() {
//		return ingredientRecipe == null ? new HashSet<IngredientRecipe>() : ingredientRecipe;
//	}
//
//	public void setIngredientRecipe(Set<IngredientRecipe> ingredientRecipe) {
//		this.ingredientRecipe = ingredientRecipe;
//	}
	
}	