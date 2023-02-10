package com.axisroom.entity;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})
public class User implements Serializable{
	@Id
	@GenericGenerator(name = "u_auto", strategy = "increment")
	@GeneratedValue(generator = "u_auto")
	 private long id;
	    private String name;
	    private String username;
	    private String email;
	    private String password;

	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(name = "users_roles", joinColumns= @JoinColumn(referencedColumnName = "id"), 
	    		inverseJoinColumns= @JoinColumn(referencedColumnName = "id"))
	    
	    private Set<Role> roles;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Set<Role> getRoles() {
			return roles;
		}
		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
					+ password + ", roles=" + roles + "]";
		}
		public User(long id, String name, String username, String email, String password, Set<Role> roles) {
			super();
			this.id = id;
			this.name = name;
			this.username = username;
			this.email = email;
			this.password = password;
			this.roles = roles;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}
