package app.rest.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.CalculatorComponent;

@Component
@Path("/calculator")
public class CalculatorController {

	@Autowired
	CalculatorComponent cc;
	
	@GET
	@Path("/add")
	public double ADD(@QueryParam("arg1")double first, @QueryParam("arg2")double second) {
		return cc.add(first, second);
	}
	
	@POST
	@Path("/subtract")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public double SUBTRACT(@FormParam("arg1")double first, @FormParam("arg2")double second) {
		return cc.subtract(first, second);
	}
	
	@GET
	@Path("/divide")
	public double DIVIDE(@QueryParam("arg1")double first, @QueryParam("arg2")double second) {
		return cc.divide(first, second);
	}
	
	@GET
	@Path("/multiply")
	public double MULTIPLY(@QueryParam("arg1")double num1, @QueryParam("arg2")double num2) {
		return cc.multiply(num1, num2);
	}
	
	@GET
	@Path("/sqrt")
	public double SQRT(@QueryParam("arg1")double num) {
		return cc.sqrt(num);
	}
}
