package com.desibazaar.rest.service;

import java.util.List;

/**
 * @author Varda Laud
 *
 */
public interface IBasicService {
	public void save(Object dto);

	public List<?> getAll();
}
