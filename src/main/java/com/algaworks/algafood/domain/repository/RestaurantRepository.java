package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	List<Restaurant> findByName(String name);
	
	Optional<Restaurant> findFirstByNameContaining(String name);

	List<Restaurant> findTop2ByNameContaining(String name);

	@Query("from Restaurant where name like %:name% and kitchen.id = :kitchenId")
	Optional<Restaurant> consultByName(String name, Long kitchenId);
	
	List<Restaurant> findByDeliveryFeeBetween(BigDecimal initialDeliveryFee, BigDecimal finalDeliveryFee);
	
	boolean existsByName(String name);
	
	int countByKitchenId(Long kitchenId);
	
}
