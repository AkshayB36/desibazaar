package com.desibazaar.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.desibazaar.rest.controller.AuctionController;
import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.vo.Item;

/**
 * @author Varda Laud
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
public class AuctionControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private AuctionController controller;

	@Mock
	private IAuctionService auctionService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getAuctions() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		Item second = new Item();
		second.setItemId(2L);
		second.setName("Micromax Canvas");

		when(auctionService.getAuctions("")).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(get("/auctions")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].itemId", is(1)))
				.andExpect(jsonPath("$[0].name", is("Samsung Galaxy")))
				.andExpect(jsonPath("$[1].itemId", is(2)))
				.andExpect(jsonPath("$[1].name", is("Micromax Canvas")));

		verify(auctionService, times(1)).getAuctions("");
		verifyNoMoreInteractions(auctionService);
	}

	@Test
	public void getAuction() throws Exception {
		Item first = new Item();
		first.setItemId(1L);
		first.setName("Samsung Galaxy");

		when(auctionService.getAuction(1L)).thenReturn(first);

		mockMvc.perform(get("/auctions/1")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$itemId", is(1)))
				.andExpect(jsonPath("$name", is("Samsung Galaxy")));

		verify(auctionService, times(1)).getAuction(1L);
		verifyNoMoreInteractions(auctionService);
	}

//	@Test
//	public void createAuction() throws Exception {
//		Item first = new Item();
//		first.setItemId(1L);
//		first.setName("Samsung Galaxy");
//
//		when(auctionService.createAuction(first)).thenReturn(first);
//
//		mockMvc.perform(get("/auctions/1")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json"))
//				.andExpect(jsonPath("$itemId", is(1)))
//				.andExpect(jsonPath("$name", is("Samsung Galaxy")));
//
//		verify(auctionService, times(1)).getAuction(1L);
//		verifyNoMoreInteractions(auctionService);
//	}

}
