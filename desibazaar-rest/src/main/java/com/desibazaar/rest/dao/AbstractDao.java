package com.desibazaar.rest.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Varda Laud
 *
 */
public abstract class AbstractDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Object entity) {
		getSession().merge(entity);
	}

	public <U, T> void delete(U id, Class<T> objectClass) {
		T entity = get(id, objectClass);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public <U, T> T get(U id, Class<T> objectClass) {
		return (T) getSession().get(objectClass, (Serializable) id);
	}

	@SuppressWarnings("unchecked")
	public <U> List<U> getAll(Class<U> objectClass) {
		return getSession().createCriteria(objectClass).list();
	}

	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
