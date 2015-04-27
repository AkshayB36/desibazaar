package com.desibazaar.rest.test.service;

/**
 * @author Sai Sarath Kuchipudi
 *
 */

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.desibazaar.rest.dao.IUserDao;
import com.desibazaar.rest.entity.EItem;
import com.desibazaar.rest.entity.EUser;
import com.desibazaar.rest.service.impl.AccountService;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
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
		when(userDao.getUser("sanghichow@gmail.com")).thenReturn(eUser);
		User user = service.getUser("sanghichow@gmail.com");

		assertEquals("sanghichow@gmail.com", user.getEmail());
		assertEquals("Sanghi", user.getName());
		assertEquals("9544 UT Dr charlotte NC", user.getAddress());
		assertEquals("9848012345", user.getNumber());
		assertEquals("password", user.getPassword());
		assertEquals(4f, user.getRating(), 0);
	}

	@Test
	public void updateUser() {
		User user = new User();
		user.setEmail("ss8990@gmail.com");
		user.setName("Sarath");
		user.setAddress("9548 UT Dr charlotte NC");
		user.setNumber("9848012345");
		user.setPassword("P@ssword");
		user.setRating(5f);
		EUser eUser = new EUser();

		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);

		service.updateUser(user);
		verify(userDao, times(1)).updateUser(any(EUser.class));
		verify(userDao, times(1)).getUser("ss8990@gmail.com");

		verifyNoMoreInteractions(userDao);
	}

	@Test
	public void createUser() {
		User user = new User();
		user.setEmail("sanghichow@gmail.com");
		user.setName("Sanghi");
		user.setAddress("9544 UT Dr charlotte NC");
		user.setNumber("9848012345");
		user.setPassword("password");
		user.setRating(4F);

		service.createUser(user);
		verify(userDao, times(1)).createUser(any(EUser.class));
		verifyNoMoreInteractions(userDao);
	}

	@Test
	public void getSubscriptions() {
		EUser eUser = new EUser();
		List<EItem> eitems = new ArrayList<EItem>();
		EItem first = new EItem();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		EItem second = new EItem();
		second.setItemId(2L);
		second.setName("Micromax Canvas");
		eitems.add(first);
		eitems.add(second);
		eUser.setSubscriptions(eitems);
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);
		List<Item> items = service.getSubscriptions("ss8990@gmail.com");
		verify(userDao, times(1)).getUser("ss8990@gmail.com");
		verifyNoMoreInteractions(userDao);
		assertEquals(1L, items.get(0).getItemId(), 0);
		assertEquals("Samsung Galaxy", items.get(0).getName());
		assertEquals(2L, items.get(1).getItemId(), 0);
		assertEquals("Micromax Canvas", items.get(1).getName());

	}

	@Test
	public void getMyItems() {
		EUser eUser = new EUser();
		List<EItem> eitems = new ArrayList<EItem>();
		EItem first = new EItem();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		EItem second = new EItem();
		second.setItemId(2L);
		second.setName("Micromax Canvas");
		eitems.add(first);
		eitems.add(second);

		eUser.setMyItems(eitems);
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);
		List<Item> items = service.getMyItems("ss8990@gmail.com");
		verify(userDao, times(1)).getUser("ss8990@gmail.com");
		verifyNoMoreInteractions(userDao);
		assertEquals(1L, items.get(0).getItemId(), 0);
		assertEquals("Samsung Galaxy", items.get(0).getName());
		assertEquals(2L, items.get(1).getItemId(), 0);
		assertEquals("Micromax Canvas", items.get(1).getName());
	}

	@Test
	public void getMyPurchases() {
		EUser eUser = new EUser();
		List<EItem> eitems = new ArrayList<EItem>();
		EItem first = new EItem();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		EItem second = new EItem();
		second.setItemId(2L);
		second.setName("Micromax Canvas");
		eitems.add(first);
		eitems.add(second);

		eUser.setMyPurchases(eitems);
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);
		List<Item> items = service.getMyPurchases("ss8990@gmail.com");
		verify(userDao, times(1)).getUser("ss8990@gmail.com");
		verifyNoMoreInteractions(userDao);
		assertEquals(1L, items.get(0).getItemId(), 0);
		assertEquals("Samsung Galaxy", items.get(0).getName());
		assertEquals(2L, items.get(1).getItemId(), 0);
		assertEquals("Micromax Canvas", items.get(1).getName());
	}

	@Test
	public void getReviews() {
		EUser eUser = new EUser();
		List<EItem> eitems = new ArrayList<EItem>();
		EItem first = new EItem();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");
		first.setReview("Good product, great benefits");
		first.setRating(4);

		EItem second = new EItem();
		second.setItemId(2L);
		second.setName("Micromax Canvas");
		second.setRating(3);
		second.setReview("Not worth the price");
		eitems.add(first);
		eitems.add(second);

		eUser.setMyPurchases(eitems);
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);
		List<Item> items = service.getMyPurchases("ss8990@gmail.com");
		verify(userDao, times(1)).getUser("ss8990@gmail.com");
		verifyNoMoreInteractions(userDao);
		assertEquals(1L, items.get(0).getItemId(), 0);
		assertEquals("Samsung Galaxy", items.get(0).getName());
		assertEquals("Good product, great benefits", items.get(0).getReview());
		assertEquals(4, items.get(0).getRating(), 0);
		assertEquals(2L, items.get(1).getItemId(), 0);
		assertEquals("Not worth the price", items.get(1).getReview());
		assertEquals(3, items.get(1).getRating(), 0);
	}

	@Test
	public void authenticate() {
		EUser eUser = new EUser();
		eUser.setEmail("ss8990@gmail.com");
		eUser.setPassword("p@ssword");
		when(userDao.getUser("ss8990@gmail.com")).thenReturn(eUser);
		User user = new User();
		user.setEmail("ss8990@gmail.com");
		user.setPassword("p@ssword");
		boolean authenticated = service.authenticate(user);

		verify(userDao, times(1)).getUser("ss8990@gmail.com");
		verifyNoMoreInteractions(userDao);
		assertEquals(true, authenticated);
	}

}
