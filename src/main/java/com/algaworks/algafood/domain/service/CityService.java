package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.City;

public interface CityService {

	City save(City city);
	
	void remove(Long id);
	
}
