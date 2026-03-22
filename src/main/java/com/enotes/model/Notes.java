package com.enotes.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
public class Notes extends BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "notes_seq")
	@SequenceGenerator(
	        name = "notes_seq",
	        sequenceName = "notes_id_seq",
	        allocationSize = 1
	)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	@ManyToOne
	private Category category;
	
}
