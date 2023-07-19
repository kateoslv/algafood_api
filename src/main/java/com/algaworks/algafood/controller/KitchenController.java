package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public List<Kitchen> listXml() {
		
		return kitchenRepository.list();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Kitchen> list() {
		
		return kitchenRepository.list();
	}
	
	@GetMapping("/{id}")
	public Kitchen findById(@PathVariable("id") Long id) {
		
		return kitchenRepository.findById(id);
	}
}
