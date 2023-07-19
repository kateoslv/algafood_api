package com.algaworks.algafood.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.PaymentMethod;
import com.algaworks.algafood.domain.repository.PaymentMethodRepository;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> list() {
		return manager.createQuery("from PaymentMethod", PaymentMethod.class)
				.getResultList();
	}

	@Override
	public PaymentMethod findById(Long id) {
		return manager.find(PaymentMethod.class, id);
	}

	@Transactional
	@Override
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return manager.merge(paymentMethod);
	}

	@Override
	public void remove(PaymentMethod paymentMethod) {
		paymentMethod = findById(paymentMethod.getId());
		manager.remove(paymentMethod);
	}
	
}
