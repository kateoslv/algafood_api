package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
