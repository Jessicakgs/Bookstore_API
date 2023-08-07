package com.example.Bookstore.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity(name = "customers")
@Table(name = "customers")
@SQLDelete(sql = "UPDATE customers SET deleted = true, updated_at = now() WHERE id = ?")
@Where(clause = "deleted = false")
public class CustomerModel extends RepresentationModel<CustomerModel> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "first_name", updatable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
		
	@Column(name = "cpf", unique = true, updatable = false)
	private String cpf;
	
	@Column(name = "phone", unique = true)
	private String phone;
	
	@Email
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "overdue")
	private boolean overdue;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "deleted")
	private boolean deleted;
	

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
	
	public CustomerModel() {
	    }

	 public CustomerModel(String firstName, String lastName, String cpf, String phone, String email, boolean overdue) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.cpf = cpf;
	        this.phone = phone;
	        this.email = email;
	        this.overdue = overdue;
	    }
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fisrtName) {
		this.firstName = fisrtName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isOverdue() {
		return overdue;
	}
	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}
	
	
}
