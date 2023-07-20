package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Kitchen;

public interface KitchenService {

	Kitchen save(Kitchen kitchen);
	
	void remove(Long id);
	
}
