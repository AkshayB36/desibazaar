package com.desibazaar.rest.test.controller;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desibazaar.rest.controller.CategoryController;
import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.vo.Category;

/**
 * @author Varda Laud
 *
 */
public class CategoryControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CategoryController controller;

	@Mock
	private IAuctionService auctionService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getCategories() throws Exception {
		Category first = new Category();
		first.setName("Fashion");

		Category second = new Category();
		second.setName("Fitness");

		when(auctionService.getCategories()).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(get("/categories")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("Fashion")))
				.andExpect(jsonPath("$[1].name", is("Fitness")));
		verify(auctionService, times(1)).getCategories();
		verifyNoMoreInteractions(auctionService);
	}

}
