package com.desibazaar.rest.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.desibazaar.rest.dao.AbstractDao;
import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.entity.ECategory;

/**
 * @author Varda Laud
 *
 */
@Repository
public class CategoryDao extends AbstractDao implements ICategoryDao {

	@Override
	public List<ECategory> getCategories() {
		return getAll(ECategory.class);
	}

}
