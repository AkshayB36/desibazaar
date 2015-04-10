package com.desibazaar.rest.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Varda Laud
 *
 */
public class Util {
	public static String getLoggedInUser(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("username");
	}
}
