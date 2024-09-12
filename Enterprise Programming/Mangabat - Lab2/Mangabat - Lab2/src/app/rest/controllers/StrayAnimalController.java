package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.StrayAnimalComponent;

@Component
@Path("/strays")
public class StrayAnimalController {
	@Autowired
	StrayAnimalComponent controller;
	
	Logger logger = LoggerFactory.getLogger(StrayAnimalController.class);
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/add")
	public void add(@FormParam("t") String type,
			@FormParam("c") String color,
			@FormParam("n") boolean neutered,
			@FormParam("d") String description) {
		controller.add(type, color, neutered, description);
	}
}
