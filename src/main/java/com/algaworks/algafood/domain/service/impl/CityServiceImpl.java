package com.algaworks.algafood.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityBeingUsedException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public City save(City city) {
		
		return cityRepository.save(city);
	}
	
	@Override
	public void remove(Long id) {
		try {
			cityRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Doesn't exist a city register with id %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityBeingUsedException(
					String.format("City with id %d could not be removed because is already in use", id));
		}
	}
}
