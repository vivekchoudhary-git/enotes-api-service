package com.enotes.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesDTO {

	private int id;
	private String title;
	private String description;
	private CategoryDTO category;             // Note: keep CategoryDTO and Category field name same in Entity and DTO classes for auto mapping eg. keep same name as category
	private Integer createdBy;
	private Date createdOn;
	private Integer updatedBy;
	private Date updatedOn;
	
}
