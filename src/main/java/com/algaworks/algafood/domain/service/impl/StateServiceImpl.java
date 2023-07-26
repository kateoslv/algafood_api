package com.algaworks.algafood.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntityBeingUsedException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;
import com.algaworks.algafood.domain.service.StateService;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public State save(State state) {
		
		return stateRepository.save(state);
	}
	
	@Override
	public void remove(Long id) {
		try {
			stateRepository.remove(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Doesn't exist a state register with id %d", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityBeingUsedException(
					String.format("State with id %d could not be removed because is already in use", id));
		}
	}
}
