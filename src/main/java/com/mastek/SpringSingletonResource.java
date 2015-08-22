package com.mastek;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import dao.AccPrefRepository;
import dao.NPLRepository;
import dto.AccountPref;
import dto.EventType;
import dto.Language;
import dto.NotificationPayload;
import dto.Preference;
import dto.TemplateLink;
import util.Contact;
import util.CustomerDetail;

@Path("spring-singleton-hello")
@Component
public class SpringSingletonResource {

	@Autowired
	private NPLRepository nplRepository;

	@Autowired
	private AccPrefRepository acntPrefRepo;

	// Consume JSON
	// Use data and push in map
    // Get User Preferences
	// Get User Details and push in map
	// Get template for preference and event type
	// Create Notification using template
    // Log notification

	@Autowired
	MongoTemplate template;
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response postNotification(String data) {
//		// What do you expect in JSON
//	}
	
	private TemplateLink getTemplateLink(EventType eventType, Language language, Preference pref){
		Query query = new Query();
		query.addCriteria(Criteria.where("eventType").is(eventType.toString()));
		query.addCriteria(Criteria.where("language").is(language.toString()));
		query.addCriteria(Criteria.where("pref").is(pref.toString()));
		List<TemplateLink> tempLink = template.find(query, TemplateLink.class);
		return tempLink.get(0);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postCheck(String data) {
		Gson gson = new Gson();
		NotificationPayload payload = gson.fromJson(data, NotificationPayload.class);
		nplRepository.save(payload);
		String result = "Data post : " + payload.getName();

//		try {
//			result = new GenerateNotification().generateNotification();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

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

	
    @GET
    @Path("/acctPref")
    @Produces(MediaType.TEXT_PLAIN)
	public Response getAcctPref(@Context HttpHeaders headers, @QueryParam("acctNo") String acctNo){
		List<AccountPref> acctPref = acntPrefRepo.findByAcctNo(acctNo);
		String result = acctPref.get(0).getEmail();
		return Response.status(201).entity(result).build();
	}
	
	
}