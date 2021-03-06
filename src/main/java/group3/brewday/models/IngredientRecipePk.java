package group3.brewday.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class IngredientRecipePk implements Serializable {

	private static final long serialVersionUID = 6381378663450828195L;
	
	private Recipe recipe;
	private Ingredient ingredient;
	
	public IngredientRecipePk() {
		super();
	}
	
	public IngredientRecipePk(Ingredient ingredient, Recipe recipe) {
		super();
		this.ingredient = ingredient;
		this.recipe = recipe;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    
}
