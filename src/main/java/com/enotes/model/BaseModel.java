package com.enotes.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass                     // refer notes for details
public class BaseModel {

	@CreatedBy
    @Column(name = "created_by",updatable = false)
	private Integer createdBy;
    
	@CreatedDate
    @Column(name = "created_on",updatable = false)
	private Date createdOn;
    
	@LastModifiedBy
    @Column(name = "updated_by")
	private Integer updatedBy;
    
	@LastModifiedDate
    @Column(name = "updated_on")
	private Date updatedOn;
	
}
