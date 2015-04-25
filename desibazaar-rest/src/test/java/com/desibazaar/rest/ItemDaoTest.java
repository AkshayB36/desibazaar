package com.desibazaar.rest;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desibazaar.rest.dao.IItemDao;

/**
 * @author Varda Laud
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class ItemDaoTest {
	@Autowired
	private IItemDao itemDao;

	@Test
	public void getAuctions() throws Exception {
		itemDao.getAuctions("");
	}
}
