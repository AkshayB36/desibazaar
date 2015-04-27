package com.desibazaar.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desibazaar.rest.controller.UserController;
import com.desibazaar.rest.service.IAccountService;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Varda Laud
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private UserController controller;

	@Mock
	private IAccountService userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void authenticateFalse() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(new User());

		when(userService.authenticate(any(User.class))).thenReturn(false);

		mockMvc.perform(
				post("/users/authenticate").content(json).contentType(
						"application/json")).andExpect(status().isBadRequest());

		verify(userService, times(1)).authenticate(any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void authenticateTrue() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(new User());

		when(userService.authenticate(any(User.class))).thenReturn(true);

		mockMvc.perform(
				post("/users/authenticate").content(json).contentType(
						"application/json")).andExpect(status().isOk());

		verify(userService, times(1)).authenticate(any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void logout() throws Exception {
		mockMvc.perform(
				get("/users/logout").sessionAttr("username", "varda@gmail.com"))
				.andExpect(status().isOk());
	}

	@Test
	public void updateUser() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(new User());

		mockMvc.perform(
				put("/users").content(json).contentType("application/json"))
				.andExpect(status().isOk());

		verify(userService, times(1)).updateUser(any(User.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void getUser() throws Exception {
		User first = new User();
		first.setEmail("varda@gmail");
		first.setName("Varda");

		when(userService.getUser("varda@gmail")).thenReturn(first);

		mockMvc.perform(get("/users/varda@gmail")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$email", is("varda@gmail")))
				.andExpect(jsonPath("$name", is("Varda")));

		verify(userService, times(1)).getUser("varda@gmail");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void getSubscripions() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		Item second = new Item();
		second.setItemId(2L);
		second.setName("Micromax Canvas");

		when(userService.getSubscriptions("varda@gmail.com")).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(
				get("/users/subscriptions").sessionAttr("username",
						"varda@gmail.com")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].itemId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Samsung Galaxy")))
				.andExpect(jsonPath("$[1].itemId", is(2)))
				.andExpect(jsonPath("$[1].name", is("Micromax Canvas")));

		verify(userService, times(1)).getSubscriptions("varda@gmail.com");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void getMyItems() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		Item second = new Item();
		second.setItemId(2L);
		second.setName("Micromax Canvas");

		when(userService.getMyItems("varda@gmail.com")).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(
				get("/users/myItems")
						.sessionAttr("username", "varda@gmail.com"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].itemId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Samsung Galaxy")))
				.andExpect(jsonPath("$[1].itemId", is(2)))
				.andExpect(jsonPath("$[1].name", is("Micromax Canvas")));

		verify(userService, times(1)).getMyItems("varda@gmail.com");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void getMyPurchases() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		Item second = new Item();
		second.setItemId(2L);
		second.setName("Micromax Canvas");

		when(userService.getMyPurchases("varda@gmail.com")).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(
				get("/users/myPurchases").sessionAttr("username",
						"varda@gmail.com")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].itemId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Samsung Galaxy")))
				.andExpect(jsonPath("$[1].itemId", is(2)))
				.andExpect(jsonPath("$[1].name", is("Micromax Canvas")));

		verify(userService, times(1)).getMyPurchases("varda@gmail.com");
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void getUserReviews() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		Item second = new Item();
		second.setItemId(2L);
		second.setName("Micromax Canvas");

		when(userService.getReviews("varda@gmail.com")).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(
				get("/users/varda@gmail.com/reviews").sessionAttr("username",
						"varda@gmail.com")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].itemId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Samsung Galaxy")))
				.andExpect(jsonPath("$[1].itemId", is(2)))
				.andExpect(jsonPath("$[1].name", is("Micromax Canvas")));

		verify(userService, times(1)).getReviews("varda@gmail.com");
		verifyNoMoreInteractions(userService);
	}
}
