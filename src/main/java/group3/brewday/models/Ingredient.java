package group3.brewday.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public static enum Type {malt, hop, yeast, sugar, additive};
	private Type type;
	private String name;
	private Double quantity;
	private String emailUser;
	
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
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public String getEmailUser() {
		return emailUser;
	}
	
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public Ingredient(Long id, Type type, String name, Double quantity, String emailUser) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.quantity = quantity;
		this.emailUser = emailUser;
	}

	public Ingredient() {
		super();
	}
	
	
}	