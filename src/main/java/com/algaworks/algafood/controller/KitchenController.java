package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.model.KitchenXmlWrapper;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public KitchenXmlWrapper listXml() {
		
		return new KitchenXmlWrapper(kitchenRepository.list());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Kitchen> list() {
		
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
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		
		return kitchenRepository.save(kitchen);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable("id") Long id,
			@RequestBody Kitchen kitchen) {
		
		Kitchen kitchenFound = kitchenRepository.findById(id);
		
		if (kitchenFound != null) {
			BeanUtils.copyProperties(kitchen, kitchenFound, "id");
			
			kitchenFound = kitchenRepository.save(kitchen);
			
			return ResponseEntity.ok(kitchenFound);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Kitchen> remove(@PathVariable("id") Long id) {
		try {
			Kitchen kitchenFound = kitchenRepository.findById(id);
			
			if (kitchenFound != null) {
				kitchenRepository.remove(kitchenFound);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
