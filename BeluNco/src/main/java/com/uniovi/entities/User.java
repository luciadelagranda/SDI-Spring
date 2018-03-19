package com.uniovi.entities;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String email;
	private String name;
	private String lastName;
	
	private Boolean peticionado= false;
	
    private String password;
    @Transient //Specifies that the property or field is not persistent. 
    private String passwordConfirm;
    
    
    @OneToMany(mappedBy="usuarioPeticionador", cascade=CascadeType.ALL)
    private Set<Peticion> peticionesEnviadas = new HashSet<Peticion>();
    
    @OneToMany(mappedBy="usuarioPeticionado", cascade=CascadeType.ALL)
    private Set<Peticion> peticionesRecibidas = new HashSet<Peticion>();
    
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="friends", joinColumns = @JoinColumn( name = "FRIEND_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> friends = new HashSet<User>();
    
    @OneToMany(mappedBy="autor", cascade=CascadeType.ALL)
    private Set<Publication> publicaciones = new HashSet<Publication>();
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	public boolean isPeticionado() {
		return peticionado;
	}



	public void setPeticionado(boolean peticionado) {
		this.peticionado = peticionado;
	}


	public Set<Peticion> getPeticionesEnviadas() {
		return peticionesEnviadas;
	}



	public void setPeticionesEnviadas(Set<Peticion> peticionesEnviadas) {
		this.peticionesEnviadas = peticionesEnviadas;
	}



	public Set<Peticion> getPeticionesRecibidas() {
		return peticionesRecibidas;
	}



	public void setPeticionesRecibidas(Set<Peticion> peticionesRecibidas) {
		this.peticionesRecibidas = peticionesRecibidas;
	}


	public void addFriend(User friend) {
		friends.add(friend);
		friend.getFriends().add(this);
		
	}



	public Set<User> getFriends() {
		return friends;
	}



	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}



	public Page<User> getFriendsList() {
		LinkedList<User> userFriends = new LinkedList<User>();
		for(User friend : friends)
			userFriends.add(friend);
		return new PageImpl<User>(userFriends);
	}


}
