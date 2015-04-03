package com.desibazaar.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desibazaar.rest.service.IAccountService;
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

	@RequestMapping(value = "/{email}/subscriptions", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getSubscripions(
			@PathVariable("email") String email) {
		return getAccountService().getSubscripions(email);
	}

	@RequestMapping(value = "/{email}/myItems", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Item> getMyItems(
			@PathVariable("email") String email) {
		return getAccountService().getMyItems(email);
	}

	public IAccountService getAccountService() {
		return accountService;
	}

}
