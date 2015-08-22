package com.mastek;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import dao.AccPrefRepository;
import dao.NPLRepository;
import dto.AccountPref;
import dto.NotificationPayload;
import util.Contact;
import util.CustomerDetail;
import util.GenerateNotification;

@Path("spring-singleton-hello")
@Component
public class SpringSingletonResource {

	@Autowired
	private NPLRepository nplRepository;

	@Autowired
	private AccPrefRepository acntPrefRepo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postCheck(String data) {
		Gson gson = new Gson();
		NotificationPayload payload = gson.fromJson(data, NotificationPayload.class);
		nplRepository.save(payload);
		String result = "Data post : " + payload.getName();

		try {
			result = new GenerateNotification().generateNotification();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result = getUserDetail();
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);
		List<Contact> contacts = customerDetail.getContacts();
		for (Contact contact : contacts) {
			if (contact.getContactType().equalsIgnoreCase("email")) {
				result = contact.getContact();
			}
		}

		return Response.status(201).entity(result).build();
	}

	// Service to get Customer Details for notification.
	private String getUserDetail() {
		String link = "http://104.131.44.187:8081/CxfRestService/rest/customerservices/getcustomerdetails";
		Client client = ClientBuilder.newClient(new ClientConfig());
		String userDetail = client.target(link).queryParam("customerId", "1")
				.request(MediaType.APPLICATION_JSON).get(String.class);
		return userDetail;
	}

	@POST
	@Path("/accpref")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccPref(String data) {
		Gson gson = new Gson();
		AccountPref accountPref = gson.fromJson(data, AccountPref.class);
		String result = "Data post : " + accountPref;
		acntPrefRepo.save(accountPref);
		return Response.status(201).entity(result).build();
	}

}
