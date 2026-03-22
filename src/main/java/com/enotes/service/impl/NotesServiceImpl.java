package com.enotes.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.enotes.dto.CategoryDTO;
import com.enotes.dto.NotesDTO;
import com.enotes.exception.ResourceNotFoundException;
import com.enotes.model.Category;
import com.enotes.model.Notes;
import com.enotes.repository.CategoryRepo;
import com.enotes.repository.NotesRepo;
import com.enotes.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService{

	@Autowired
	private NotesRepo notesRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Boolean saveNotesDetails(NotesDTO notesDTO) throws ResourceNotFoundException {
		
		// first checking whether category is valid or not
		checkCategoryExists(notesDTO.getCategory());
		
		Notes notes = modelMapper.map(notesDTO, Notes.class);         // Note: note prefer this mapping only for simple DTO classes,Do Manual mapping for DTOs having nested classes
		Notes savedNotes = notesRepo.save(notes);
		
		if(!ObjectUtils.isEmpty(savedNotes)) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<NotesDTO> getAllNotesDetails() {
		
		List<Notes> notesList = notesRepo.findAll();
		
		List<NotesDTO> notesDTOList = notesList.stream().map(notes -> modelMapper.map(notes, NotesDTO.class)).toList();
		
		return notesDTOList;
	}

	
	private void checkCategoryExists(CategoryDTO categoryDTO) throws ResourceNotFoundException {
		
		categoryRepo.findById(categoryDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Category"));
		
	}
	
	
}
