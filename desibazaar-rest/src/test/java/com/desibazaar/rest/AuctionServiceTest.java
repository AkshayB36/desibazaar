package com.desibazaar.rest;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desibazaar.rest.dao.ICategoryDao;
import com.desibazaar.rest.dao.IItemDao;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.service.impl.AuctionService;



@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class AuctionServiceTest {

	
	@InjectMocks
	private AuctionService auctionservice;

	@Mock
	private IUserDao userDao;
	
	@Mock
	private ICategoryDao catDao;
	
	@Mock
	private IItemDao itemDao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	
	
}
