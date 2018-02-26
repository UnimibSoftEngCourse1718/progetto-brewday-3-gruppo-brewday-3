package group3.brewday.models;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
@AssociationOverride(name = "ingredient", joinColumns = @JoinColumn(name = "ingredient_id")),
@AssociationOverride(name = "recipe", joinColumns = @JoinColumn(name = "recipe_id"))})
@Table(name = "ingredient_recipe")
public class IngredientRecipe implements Serializable{
	
	private static final long serialVersionUID = -1085688731725356772L;

	private Double quantity;
	
	
	private IngredientRecipePk ingredientRecipePk = new IngredientRecipePk();
	
	@Transient
	public Recipe getRecipe() {
		return this.ingredientRecipePk.getRecipe();
	}
	
	public void setRecipe(Recipe recipe) {
		this.ingredientRecipePk.setRecipe(recipe);
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredientRecipePk.setIngredient(ingredient);
	}
	
	@Transient
	public Ingredient getIngredient() {
		return this.ingredientRecipePk.getIngredient();
	}

	@EmbeddedId
	public IngredientRecipePk getIngredientRecipePk() {
		return ingredientRecipePk;
	}
	
	public void setIngredientRecipePk(IngredientRecipePk ingredientRecipePk) {
		this.ingredientRecipePk = ingredientRecipePk;
	}
    
    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
	public IngredientRecipe() {
		super();
	}
	
	public IngredientRecipe(Double quantity, IngredientRecipePk ingredientRecipePk) {
		this();
		this.ingredientRecipePk = ingredientRecipePk;
		this.quantity = quantity;
	}
    
}
