package com.desibazaar.rest;

/**
 * @author Sai Sarath Kuchipudi
 *
 */

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.entity.ECategory;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class CategoryDaoTest {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Test
	public void getCategories() {
		List<ECategory> ecats=categoryDao.getCategories();
		assertEquals(4,ecats.size());
		assertEquals("Electronics",ecats.get(0).getName());
		assertEquals(null,ecats.get(0).getDescription());
	}
}
