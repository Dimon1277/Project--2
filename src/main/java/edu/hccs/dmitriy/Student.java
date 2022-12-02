package edu.hccs.dmitriy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
	@JsonProperty("id")
	String ID;
	@JsonProperty("first_name")
	String firstName;
	@JsonProperty("gpa")
	Double gpa;
	@JsonProperty("email")
	String email;
	@JsonProperty("gender")
	String gender;

}
