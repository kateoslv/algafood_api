package com.algaworks.algafood.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping
	public List<Restaurant> findAll() {
		
		return restaurantRepository.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable("id") Long id) {
		
		Restaurant restaurant = restaurantRepository.findById(id);
		
		if (restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.save(restaurant);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
			@RequestBody Restaurant restaurant) {
		try {
			Restaurant restaurantFound = restaurantRepository.findById(id);
			
			if (restaurantFound != null) {
				BeanUtils.copyProperties(restaurant, restaurantFound, "id");
				
				restaurantFound = restaurantService.save(restaurantFound);
				
				return ResponseEntity.ok(restaurantFound);
			}
			return ResponseEntity.notFound().build();			
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable("id") Long id,
			@RequestBody Map<String, Object> fields) {
		
		Restaurant restaurantFound = restaurantRepository.findById(id);
		
		if (restaurantFound == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(fields, restaurantFound);
		
		return update(id, restaurantFound);
	}

	private void merge(Map<String, Object> originFields, Restaurant target) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant originRestaurant = objectMapper.convertValue(originFields, Restaurant.class);
		
		originFields.forEach((name, value) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, name);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, originRestaurant);
			
			ReflectionUtils.setField(field, target, newValue);
		});
	}
	
}
