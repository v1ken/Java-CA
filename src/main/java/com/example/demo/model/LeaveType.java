package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leavetype")
@Data
@NoArgsConstructor
public class LeaveType {
	@Id
	@Column(name = "typeid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String typeId;
	@Column(name = "type")
	private String type;
	@Column(name = "description")
	private String description;
	
	public LeaveType(String typeId, String type, String description) {
		super();
		this.typeId = typeId;
		this.type = type;
		this.description = description;
	}
	

	
	
}
