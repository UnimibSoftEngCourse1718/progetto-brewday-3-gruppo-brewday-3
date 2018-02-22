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
@Table(name = "ingredient_user")
@AssociationOverrides({
@AssociationOverride(name = "ingredient", joinColumns = @JoinColumn(name = "ingredient_id")),
@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "user_id"))})
public class IngredientUser implements Serializable {

	private static final long serialVersionUID = -1738304256355815937L;

	private Double quantity;

	@Column(name = "quantity")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	private IngredientUserPk ingredientUserPk = new IngredientUserPk();
		
	@Transient
	public User getUser() {
		return this.ingredientUserPk.getUser();
	}
	
	public void setUser(User user) {
		this.ingredientUserPk.setUser(user);
	}
	
	
	public void setIngredient(Ingredient ingredient) {
		this.ingredientUserPk.setIngredient(ingredient);
	}
	
	@Transient
	public Ingredient getIngredient() {
		return this.ingredientUserPk.getIngredient();
	}

	@EmbeddedId
	public IngredientUserPk getIngredientUserPk() {
		return ingredientUserPk;
	}

	public void setIngredientUserPk(IngredientUserPk ingredientUserPk) {
		this.ingredientUserPk = ingredientUserPk;
	}
	
	public IngredientUser() {
		super();
	}
	
	public IngredientUser(Double quantity, IngredientUserPk ingredientUserPk) {
		this();
		this.ingredientUserPk = ingredientUserPk;
		this.quantity = quantity;
	}

}
