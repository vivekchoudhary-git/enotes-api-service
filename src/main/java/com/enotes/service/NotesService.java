package com.enotes.service;

import java.util.List;

import com.enotes.dto.NotesDTO;
import com.enotes.exception.ResourceNotFoundException;

public interface NotesService {

	public Boolean saveNotesDetails(NotesDTO NotesDTO) throws ResourceNotFoundException;
	public List<NotesDTO> getAllNotesDetails();
	
}
