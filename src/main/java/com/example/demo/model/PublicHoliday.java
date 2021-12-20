package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicholiday")
@Data
@NoArgsConstructor
public class PublicHoliday {
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private Date date;
	public PublicHoliday(String name, String description, Date date) {
		super();
		this.name = name;
		this.description = description;
		this.date = date;
	}


}
