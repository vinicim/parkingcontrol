package com.api.parkingcontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column(unique = true)
	private String cpf;
	
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

}
