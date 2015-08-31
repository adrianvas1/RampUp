package com.endava.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import com.endava.config.MessagesConfiguration;
import com.endava.config.PATCH;
import com.endava.dto.DocumentDTO;
import com.endava.mongodb.MongoDBService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path(value = "/crud")
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
	public Response createDoc(@Valid DocumentDTO dto) {
		service.create(name);
		String response = "Document created succesfully!";
		return Response.ok(response, MediaType.APPLICATION_XML).build();
	}

	@PATCH
	@Path(value = "/update/{name}")
	public Response updateDoc(@PathParam("name") String name) {
		service.update(name);
		String response = "Document with 'name': " + name
				+ ", was updated succesfully!";
		return Response.ok(response, MediaType.APPLICATION_XML).build();
	}

	@DELETE
	@Path(value = "/delete/{name}")
	public Response deleteDoc(@PathParam("name") String name) {
		service.delete(name);
		String response = "Document with 'name': " + name
				+ ", was deleted succesfully!";
		return Response.ok(response, MediaType.APPLICATION_XML).build();
	}

	@POST
	@Path(value = "/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(

	@FormDataParam("file") InputStream uploadedInputStream,

	@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "D:\\uploaded\\" + fileDetail.getFileName();
		try {
			service.writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String output = "File uploaded to: " + uploadedFileLocation;
		return Response.status(200).entity(output).build();
	}

	// GET file from location
	private static final String TXT_FILE = "D:\\uploaded\\data.odt";

	@GET
	@Path("/download")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response getTextFile() {

		File file = new File(TXT_FILE);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=\"data.odt\"");
		return response.build();
	}
}