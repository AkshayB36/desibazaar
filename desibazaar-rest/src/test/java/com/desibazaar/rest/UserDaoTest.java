package com.desibazaar.rest;

/**
 * @author Sai Sarath Kuchipudi
 *
 */

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EUser;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class UserDaoTest {
	@Autowired
	private IUserDao userDao;

	@Test
	public void createUser() {
		EUser user=new EUser();
		user.setAddress("9544 University Terrace Drive, Charlotte, NC");
		user.setEmail("ss@yahoo.com");
		user.setName("Bob");
		user.setNumber("8393920202");
		user.setPassword("password");
		user.setRating(3.5f);
		userDao.createUser(user);
	}
	
	@Test
	public void updateUser() {
		EUser user2=userDao.getUser("ss8990@gmail.com");
		user2.setAddress("9548 UT Dr, charlotte, NC");
		user2.setName("Sai Sarath");
		userDao.updateUser(user2);
	}
	
	
	@Test
	public void getUser() {
		EUser user2=userDao.getUser("ss8990@gmail.com");
		assertEquals("sarath",user2.getName());
		assertEquals("9848012345",user2.getNumber());
		assertEquals("sarath",user2.getPassword());
		assertEquals("UT Dr Charlotte",user2.getAddress());
		assertEquals(0f,user2.getRating(),0);
	}
}
