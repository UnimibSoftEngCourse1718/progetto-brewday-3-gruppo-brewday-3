package group3.brewday.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tool {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Double capacity;
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
	
	public Double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	public String getEmailUser() {
		return emailUser;
	}
	
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public Tool(Long id, String name, Double capacity, String emailUser) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.emailUser = emailUser;
	}
	
	public Tool(){
		
	}
}
