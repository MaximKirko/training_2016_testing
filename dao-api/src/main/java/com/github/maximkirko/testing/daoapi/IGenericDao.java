package com.github.maximkirko.testing.daoapi;

import java.io.Serializable;
import java.util.List;

import com.github.maximkirko.testing.datamodel.models.AbstractModel;

public interface IGenericDao<T extends AbstractModel, PK extends Serializable> {
	
	T get(PK id);

	List<T> getAll();
	
	PK insert(T entity);

	void update(T newEntity);

	void delete(PK id);

}