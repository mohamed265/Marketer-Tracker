package com.mohamed265.marketertracker.webservice;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.mohamed265.marketertracker.entity.User;
import com.mohamed265.marketertracker.service.UserService;
import com.mohamed265.marketertracker.util.Constants;

/**
 * @author mohamed265
 */
@Path("/user")
public class UserWebService {

	@Autowired
	private UserService userService;

	public UserWebService() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@PUT
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	public User signup(@FormParam(Constants.User.NAME) String name, @FormParam(Constants.User.EMAIL) String email,
			@FormParam(Constants.User.PASSWORD) String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setIsActive(false);
		userService.save(user);
		return user;
	}

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public User loginWithEmail(@QueryParam(Constants.User.EMAIL) String email) {
		String PASSWORD = Constants.PASSWORD;
		return userService.loginByEmail(email, PASSWORD);
	}

	// @POST
	// @Path("/login")
	// @Produces(MediaType.APPLICATION_JSON)
	// public User loginWithUserName(@PathParam(Constants.User.USERNAME) String
	// username,
	// @PathParam(Constants.User.PASSWORD) String password) {
	// return userService.loginByUsername(username, password);
	// }

}