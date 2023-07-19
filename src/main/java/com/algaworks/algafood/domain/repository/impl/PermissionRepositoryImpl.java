package com.algaworks.algafood.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permission> list() {
		return manager.createQuery("from Permission", Permission.class)
				.getResultList();
	}

	@Override
	public Permission findById(Long id) {
		return manager.find(Permission.class, id);
	}

	@Transactional
	@Override
	public Permission save(Permission permission) {
		return manager.merge(permission);
	}

	@Override
	public void remove(Permission permission) {
		permission = findById(permission.getId());
		manager.remove(permission);
	}
	
}
