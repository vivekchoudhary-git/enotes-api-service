package com.enotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enotes.dto.NotesDTO;
import com.enotes.exception.ResourceNotFoundException;
import com.enotes.service.NotesService;
import com.enotes.util.CommonUtil;

@RestController
@RequestMapping("api/v1/notes")
public class NotesController {

	@Autowired
	private NotesService notesServiceImpl;
	
	@PostMapping("/saveNotes")
	public ResponseEntity<Object> saveNotesData(@RequestBody NotesDTO notesDTO) throws ResourceNotFoundException {
		
		Boolean savedNotes = notesServiceImpl.saveNotesDetails(notesDTO);
		if(!savedNotes) {
			return CommonUtil.createErrorResponseMessage("Notes have not been saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return CommonUtil.createBuildResponseMessage("Notes have been saved successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAllNotes")
	public ResponseEntity<Object> viewAllNotesData() {
		
		List<NotesDTO> notesDtoList = notesServiceImpl.getAllNotesDetails();
		if(CollectionUtils.isEmpty(notesDtoList)) {
			return CommonUtil.createErrorResponseMessage("Could not get Notes List", HttpStatus.NOT_FOUND);
		}
		
		return CommonUtil.createBuildResponse(notesDtoList, HttpStatus.OK);
	}
	
}
