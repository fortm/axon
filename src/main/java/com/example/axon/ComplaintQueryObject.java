package com.example.axon;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintQueryObject {

	@Id
	private String complaintId;

	private String company;

	private String description;
}
