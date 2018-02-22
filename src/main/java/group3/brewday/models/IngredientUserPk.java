package group3.brewday.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class IngredientUserPk implements Serializable {

	private static final long serialVersionUID = 933080256137578219L;
	
	private Ingredient ingredient;
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
