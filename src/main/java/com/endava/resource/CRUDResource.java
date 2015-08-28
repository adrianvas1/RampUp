package com.endava.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.endava.config.MessagesConfiguration;
import com.endava.config.PATCH;
import com.endava.mongodb.MongoDBService;

@Path(value = "/crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CRUDResource {

    private final MessagesConfiguration conf;
    private MongoDBService service;

    public CRUDResource(MessagesConfiguration conf) {
        this.conf = conf;
        service = new MongoDBService();
    }

    @GET
    @Path(value = "/read")
    public Response readDoc(@QueryParam("name") String name) {
        String json = service.read(name);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path(value = "/create/{name}")
    public void createDoc(@PathParam("name") String name) {
        service.create(name);
    }
    
    @PATCH
    @Path(value="/update/{name}")
    public void updateDoc(@PathParam("name") String name) {
        service.update(name);
    }
    
    @DELETE
    @Path(value="/delete/{name}") 
    public void deleteDoc(@PathParam("name") String name) {
    	service.delete(name);
    }
}