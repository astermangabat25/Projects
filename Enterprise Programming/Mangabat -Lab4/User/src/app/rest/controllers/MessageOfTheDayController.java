package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.UserComponent;
import app.entities.User;

@Component
@Path("/user")
public class MessageOfTheDayController {
	@Autowired
	UserComponent md;
	
	@GET
	@Path("/finduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@QueryParam("p") Long pk) {
		return md.findUser(pk);
	}
}
