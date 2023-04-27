package com.luv2code.springboot.crudapp.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class course {
	@Id
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="id",insertable=false, updatable=false)
	private UUID id;
	
	@Column(name="Course_name")
	private String Course_name;
	
	@Column(name="mentor")
	private String mentor_name;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="student_id")
	private UUID student_id;
	
	public course() {
	}

	public course(UUID id, String course_name, String mentor_name, int rating, UUID student_id) {
		this.id = id;
		this.Course_name = course_name;
		this.mentor_name = mentor_name;
		this.rating = rating;
		this.student_id = student_id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCourse_name() {
		return Course_name;
	}

	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}

	public String getMentor_name() {
		return mentor_name;
	}

	public void setMentor_name(String mentor_name) {
		this.mentor_name = mentor_name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public UUID getStudent_id() {
		return student_id;
	}

	public void setStudent_id(UUID student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "course [id=" + id + ", Course_name=" + Course_name + ", mentor_name=" + mentor_name + ", rating="
				+ rating + ", student_id=" + student_id + "]";
	}
	
	
}
