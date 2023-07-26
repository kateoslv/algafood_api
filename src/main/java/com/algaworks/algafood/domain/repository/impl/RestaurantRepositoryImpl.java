package com.algaworks.algafood.domain.repository.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	public EntityManager manager;
	
	@Override
	public List<Restaurant> find(String name, BigDecimal initialDeliveryFee, BigDecimal finalDeliveryFee) {
		
		var jpql = "from Restaurant where name like :name "
				+ "and deliveryFee between :initialDeliveryFee and :finalDeliveryFee";
		
		return manager.createQuery(jpql, Restaurant.class)
				.setParameter("name", "%" + name + "%")
				.setParameter("initialDeliveryFee", initialDeliveryFee)
				.setParameter("finalDeliveryFee", finalDeliveryFee)
				.getResultList();
	}
	
}
