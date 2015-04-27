package com.desibazaar.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desibazaar.rest.service.IAccountService;
import com.desibazaar.rest.util.Util;
import com.desibazaar.rest.vo.Item;
import com.desibazaar.rest.vo.User;

/**
 * @author Varda Laud
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {
	private final static Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> authenticate(
			@RequestBody User user, HttpServletRequest request) {
		LOGGER.debug("Received authenticate() request User Name : "
				+ user.getEmail());
		if (!getAccountService().authenticate(user)) {
			LOGGER.debug("Invalid authenticate() request User Name : "
					+ user.getEmail());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		request.getSession().setAttribute("username", user.getEmail());
		LOGGER.debug("Valid authenticate() request User Name : "
				+ user.getEmail());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody void logout(HttpServletRequest request) {
		LOGGER.debug("Received logout() request User Name : "
				+ Util.getLoggedInUser(request));
		request.getSession().setAttribute("username", null);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void createUser(@RequestBody User user) {
		LOGGER.debug("Received createUser() request User Name : "
				+ user.getEmail());
		getAccountService().createUser(user);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody void updateUser(@RequestBody User user) {
		LOGGER.debug("Received updateUser() request User Name : "
				+ user.getEmail());
		getAccountService().updateUser(user);
	}

	@RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User getUser(@PathVariable("email") String email) {
		LOGGER.debug("Received getUser() request User Name : " + email);
		return getAccountService().getUser(email);
	}

	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getSubscripions(HttpServletRequest request) {
		LOGGER.debug("Received getSubscripions() request User Name : "
				+ Util.getLoggedInUser(request));
		return getAccountService().getSubscriptions(
				Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/myItems", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getMyItems(HttpServletRequest request) {
		LOGGER.debug("Received getMyItems() request User Name : "
				+ Util.getLoggedInUser(request));
		return getAccountService().getMyItems(Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/myPurchases", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getMyPurchases(HttpServletRequest request) {
		LOGGER.debug("Received getMyPurchases() request User Name : "
				+ Util.getLoggedInUser(request));
		return getAccountService()
				.getMyPurchases(Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "{email}/reviews", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getUserReviews(
			@PathVariable("email") String email) {
		LOGGER.debug("Received getUserReviews() request User Name : " + email);
		return getAccountService().getReviews(email);
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}
