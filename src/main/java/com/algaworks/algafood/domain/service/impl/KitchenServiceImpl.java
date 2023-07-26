package com.algaworks.algafood.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityBeingUsedException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;

@Service
public class KitchenServiceImpl implements KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Override
	public Kitchen save(Kitchen kitchen) {
		
		return kitchenRepository.save(kitchen);
	}
	
	@Override
	public void remove(Long id) {
		try {
			kitchenRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Doesn't exist a kitchen register with id %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityBeingUsedException(
					String.format("Kitchen with id %d could not be removed because is already in use", id));
		}
	}
}
