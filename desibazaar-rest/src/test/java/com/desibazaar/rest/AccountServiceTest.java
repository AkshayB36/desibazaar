package com.desibazaar.rest;


/**
 * @author Sai Sarath Kuchipudi
 *
 */

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.desibazaar.rest.converter.EntityToDtoConverter;
import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.service.impl.AccountService;
import com.desibazaar.rest.vo.User;



@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class AccountServiceTest {
		

	@InjectMocks
	private AccountService service;

	@Mock
	private IUserDao userDao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	
	@Test
	public void getUser() {
		EUser eUser = new EUser();
		eUser.setEmail("sanghichow@gmail.com");
		eUser.setName("Sanghi");
		eUser.setAddress("9544 UT Dr charlotte NC");
		eUser.setNumber("9848012345");
		eUser.setPassword("password");
		eUser.setRating(4f);
		when(userDao.getUser("sanghichow@gmail.com")).thenReturn(
				eUser);
		User user=service.getUser("sanghichow@gmail.com");
		
		assertEquals("sanghichow@gmail.com",user.getEmail());
		assertEquals("Sanghi",user.getName());
		assertEquals("9544 UT Dr charlotte NC",user.getAddress());
		assertEquals("9848012345",user.getNumber());
		assertEquals("password",user.getPassword());
		assertEquals(4f,user.getRating(),0);
	}
	
	@Test
	public void updateUser() {
			EUser eUser = new EUser();
		eUser.setEmail("ss8990@gmail.com");
		eUser.setName("Sarath");
		eUser.setAddress("9548 UT Dr charlotte NC");
		eUser.setNumber("9848012345");
		eUser.setPassword("P@ssword");
		eUser.setRating(5f);
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(
				eUser);
		User user=service.getUser("ss8990@gmail.com");
		
		assertEquals("ss8990@gmail.com",user.getEmail());
		user.setAddress("9542 brroklyn street NY");
		user.setName("Sai Sarath");
		user.setNumber("7866778976");
		user.setPassword("pass");
		user.setRating(4.5f);
		service.updateUser(user);
		assertEquals("Sai Sarath",user.getName());
		assertEquals("9542 brroklyn street NY",user.getAddress());
		assertEquals("7866778976",user.getNumber());
		assertEquals("pass",user.getPassword());
		assertEquals(4.5f,user.getRating(),0);
		}
	
	@Test
	public void createUser() {
			EUser eUser = new EUser();
		eUser.setEmail("sanghichow@gmail.com");
		eUser.setName("Sanghi");
		eUser.setAddress("9544 UT Dr charlotte NC");
		eUser.setNumber("9848012345");
		eUser.setPassword("password");
		eUser.setRating(4F);
		
		User user=EntityToDtoConverter.convertEUserToUser(eUser);
		service.createUser(user);
		
		assertEquals("sanghichow@gmail.com",user.getEmail());
		assertEquals("Sanghi",user.getName());
		assertEquals("9544 UT Dr charlotte NC",user.getAddress());
		assertEquals("9848012345",user.getNumber());
		assertEquals("password",user.getPassword());
	}
	
	@Test
	public void getSubscripions() {
		EUser eUser = new EUser();
		
		when(userDao.getUser("ss8990@gmail.com").getSubscriptions()).thenReturn(
				(List<EItem>) eUser);
		service.getUser("ss8990@gmail.com").getSubscriptions();
	}
	
}
