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
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenService kitchenService;
	
	@GetMapping
	public List<Kitchen> findAll() {
		
		return kitchenRepository.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> findById(@PathVariable("id") Long id) {
		
		Kitchen kitchen = kitchenRepository.findById(id);
		
		if (kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Kitchen kitchen) {
		try {
			kitchen = kitchenService.save(kitchen);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(kitchen);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable("id") Long id,
			@RequestBody Kitchen kitchen) {
		
		Kitchen kitchenFound = kitchenRepository.findById(id);
		
		if (kitchenFound != null) {
			BeanUtils.copyProperties(kitchen, kitchenFound, "id");
			
			kitchenFound = kitchenService.save(kitchen);
			
			return ResponseEntity.ok(kitchenFound);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			kitchenService.remove(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityBeingUsedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
}
