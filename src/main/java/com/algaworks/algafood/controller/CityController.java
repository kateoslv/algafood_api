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
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public List<City> findAll() {
		
		return cityRepository.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> findById(@PathVariable Long id) {
		City city = cityRepository.findById(id);
		
		if (city != null) {
			return ResponseEntity.ok(city);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody City city) {
		try {
			city = cityRepository.save(city);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(city);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,
			@RequestBody City city) {
		try {
			City cityFound = cityRepository.findById(id);
			
			if (cityFound != null) {
				BeanUtils.copyProperties(city, cityFound, "id");
				
				cityFound = cityService.save(cityFound);
				
				return ResponseEntity.ok(cityFound);
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
			cityService.remove(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityBeingUsedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
	
}
