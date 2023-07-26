package com.algaworks.algafood.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityBeingUsedException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Override
	public Restaurant save(Restaurant restaurant) {
		
		Long kitchenId = restaurant.getKitchen().getId();
		
		Kitchen kitchen = kitchenRepository.findById(kitchenId)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Doesn't exist a kitchen with id %d", kitchenId)));
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	@Override
	public void remove(Long id) {
		try {
			restaurantRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Doesn't exist a restaurant register with id %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityBeingUsedException(
					String.format("Restaurant with id %d could not be removed because is already in use", id));
		}
	}

}
