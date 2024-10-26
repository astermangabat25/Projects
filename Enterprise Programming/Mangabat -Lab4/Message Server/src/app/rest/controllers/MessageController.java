package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.MessageComponent;
import app.entities.Message;

@Component
@Path("/message")
public class MessageController {

    @Autowired
    private MessageComponent mc;

    @GET
    @Path("/getquote")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message getQuote(@QueryParam("c") String c) {
        return mc.getQuote(c);
    }
}
