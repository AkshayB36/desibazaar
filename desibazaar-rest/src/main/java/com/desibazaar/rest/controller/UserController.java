package com.desibazaar.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private IAccountService accountService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<String> authenticate(
			@RequestBody User user, HttpServletRequest request) {
		if (!getAccountService().authenticate(user)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		request.getSession().setAttribute("username", user.getEmail());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody void logout(HttpServletRequest request) {
		request.getSession().setAttribute("username", null);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void createUser(@RequestBody User user) {
		getAccountService().createUser(user);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public @ResponseBody void updateUser(@RequestBody User user) {
		getAccountService().updateUser(user);
	}

	@RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User getUser(@PathVariable("email") String email) {
		return getAccountService().getUser(email);
	}

	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getSubscripions(HttpServletRequest request) {
		return getAccountService().getSubscripions(
				Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/myItems", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getMyItems(HttpServletRequest request) {
		return getAccountService().getMyItems(Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/myPurchases", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getMyPurchases(HttpServletRequest request) {
		return getAccountService()
				.getMyPurchases(Util.getLoggedInUser(request));
	}

	@RequestMapping(value = "/reviews", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getUserReviews(HttpServletRequest request) {
		return getAccountService().getReviews(Util.getLoggedInUser(request));
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}
