package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@NodeEntity
public class Person  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id @GeneratedValue
	private Long id;

    @NonNull
	private String fullName;

    @NonNull
	private String emailId;

    @NonNull
	private String phoneNumber;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private Date updateDate;

	@JsonIgnore
	@Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
	public Set<Person> teammates;

	

	public Person(String fullName, String emailId, String phoneNumber) {
		this.fullName = fullName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}

	@JsonIgnore
	public void worksWith(Person person) {
		if (teammates == null) {
			teammates = new HashSet<>();
		}
		teammates.add(person);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<Person> getTeammates() {
		return teammates;
	}

	public void setTeammates(Set<Person> teammates) {
		this.teammates = teammates;
	}

	public Long getId() {
		return id;
	}
  

}
