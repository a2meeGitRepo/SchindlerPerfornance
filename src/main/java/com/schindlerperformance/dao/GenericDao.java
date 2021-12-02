package com.schindlerperformance.dao;

import java.util.List;

import com.schindlerperformance.exceptions.TechnicalException;

/**
 * 
 * @param <E>
 * @param <K>
 */
public interface GenericDao<E, K> {
	public void add(E entity);

	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E find(K key);

	public List<E> getAll() throws TechnicalException;
}