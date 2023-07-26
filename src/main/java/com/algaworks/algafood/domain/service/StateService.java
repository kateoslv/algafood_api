package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.State;

public interface StateService {

	State save(State state);
	
	void remove(Long id);
	
}
