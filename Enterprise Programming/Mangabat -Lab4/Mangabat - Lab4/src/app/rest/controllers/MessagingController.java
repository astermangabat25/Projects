package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.MessagingComponent;

@Component
@Path("/message")
public class MessagingController{
	
	@Autowired
	MessagingComponent mc;
    
    @GET
    @Path("/check")
    public String messagingCont(@QueryParam("p") Long pk, @QueryParam("c") String category) throws Exception{
    	return mc.messaging(pk, category);
    }
}
