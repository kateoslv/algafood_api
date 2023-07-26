package com.algaworks.algafood.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/tests")
public class TestController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("/search_name")
	public List<Restaurant> findByName(String name) {
		
		return restaurantRepository.findByName(name);
	}
	
	@GetMapping("/search_first_name")
	public Optional<Restaurant> findFirstByName(String name) {
		
		return restaurantRepository.findFirstByNameContaining(name);
	}
	
	@GetMapping("/search_name_top2")
	public List<Restaurant> findTop2ByNameContaining(String name) {
		
		return restaurantRepository.findTop2ByNameContaining(name);
	}
	
	@GetMapping("/search_name_and_kitchen_id")
	public Optional<Restaurant> findByNameAndKitchenId2(String name, Long kitchenId) {
		
		return restaurantRepository.consultByName(name, kitchenId);
	}
	
	@GetMapping("/search_delivery_fee")
	public List<Restaurant> findByDeliveryFee(BigDecimal initialDeliveryFee, BigDecimal finalDeliveryFee) {
		
		return restaurantRepository.findByDeliveryFeeBetween(initialDeliveryFee, finalDeliveryFee);
	}
	
	@GetMapping("/search_name_and_delivery_fee")
	public List<Restaurant> findByNameAndDeliveryFee(String name,
			BigDecimal initialDeliveryFee, BigDecimal finalDeliveryFee) {
		
		return restaurantRepository.find(name, initialDeliveryFee, finalDeliveryFee);
	}
	
	@GetMapping("/exists_by_name")
	public boolean findByExistentName(String name) {
		
		return restaurantRepository.existsByName(name);
	}
	
	@GetMapping("/count")
	public int countKitchenId(Long kitchenId) {
		
		return restaurantRepository.countByKitchenId(kitchenId);
	}
	
}
