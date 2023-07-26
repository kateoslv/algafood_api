package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityBeingUsedException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private StateService stateService;

	@GetMapping
	public List<State> findAll() {
		
		return stateRepository.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> findById(@PathVariable Long id) {
		
		State state = stateRepository.findById(id);
		
		if (state != null) {
			return ResponseEntity.ok(state);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody State state) {
		try {
			state = stateRepository.save(state);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(state);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
			@RequestBody State state) {
		try {
			State stateFound = stateRepository.findById(id);
			
			if (stateFound != null) {
				BeanUtils.copyProperties(state, stateFound, "id");
				
				state = stateRepository.save(state);
				return ResponseEntity.ok(stateFound);
			}
			return ResponseEntity.notFound().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			stateService.remove(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityBeingUsedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
}
