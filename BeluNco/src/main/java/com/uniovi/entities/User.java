package com.uniovi.entities;

import javax.persistence.*;


@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String email;
	private String name;
	private String lastName;
	
    private String password;
    @Transient //Specifies that the property or field is not persistent. 
    private String passwordConfirm;
    
    

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}
	

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String dni) {
		this.email = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return this.name + " " + this.lastName;
	}



	
	

}