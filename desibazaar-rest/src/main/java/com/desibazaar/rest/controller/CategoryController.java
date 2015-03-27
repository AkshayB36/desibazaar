package com.desibazaar.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desibazaar.rest.service.IAuctionService;
import com.desibazaar.rest.vo.Category;

/**
 * @author Varda Laud
 *
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private IAuctionService auctionService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Category> getCategories() {
		return getAuctionService().getCategories();
	}

	private IAuctionService getAuctionService() {
		return auctionService;
	}
}
