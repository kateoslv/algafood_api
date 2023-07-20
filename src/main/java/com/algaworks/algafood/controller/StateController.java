package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;

	@GetMapping
	public List<State> findAll() {
		
		return stateRepository.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<State> findById(@PathVariable("id") Long id) {
		
		State state = stateRepository.findById(id);
		
		if (state != null) {
			return ResponseEntity.ok(state);
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
