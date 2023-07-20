package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurant;

public interface RestaurantService {

	Restaurant save(Restaurant restaurant);
	
	void remove(Long id);
	
}
