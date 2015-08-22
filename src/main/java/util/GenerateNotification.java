package util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import dao.AccPrefRepository;
import dao.NotifLogRepository;
import dto.AccountPref;
import dto.EventType;
import dto.Language;
import dto.NotificationLog;
import dto.Preference;
import dto.TemplateLink;

@Component("generateNotification")
public class GenerateNotification {
	
	@Autowired
	public AccPrefRepository acntPrefRepo;
	
	@Autowired
	public MongoTemplate template;
	
	@Autowired
	public NotifLogRepository notifLogRepo;
	
	private Map<String, Object> dataAttributes = new HashMap<String, Object>();
	private Map<String, Object> custAttributes = new HashMap<String, Object>();
	private List<AccountPref> accntPrefList = null; 

	
	public void generateNotification() throws IOException {
		
		Resource resource = new ClassPathResource("velocity.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();

		VelocityContext context = new VelocityContext();
		
		for (Map.Entry<String, Object> entry : dataAttributes.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		
		for (Map.Entry<String, Object> entry : custAttributes.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		
		List<Contact> contacts = (List<Contact>)context.get("contacts");
		EventType eventType = EventType.getEventType(""+dataAttributes.get("EventType"));
	

		this.accntPrefList= acntPrefRepo.findByAcctNo(context.get("AccountNumber")+"");
		List<Preference> prefs = null;
		Language lang;
		AccountPref accntPref =null;
		if (accntPrefList.isEmpty())
		{
			prefs=new ArrayList<Preference>();
			prefs.add(Preference.EMAIL);
			lang =  Language.ENGLISH;
			accntPref = new AccountPref();
			accntPref.setAcctNo(context.get("AccountNumber")+"");
			accntPref.setLanguage(lang);
			accntPref.setNotify(true);
			accntPref.setPrefs(prefs);
			for(Contact contact: contacts){
				if(contact.getContactType().equalsIgnoreCase("Mobile")){
					accntPref.setSms(contact.getContact());
				} else if(contact.getContactType().equalsIgnoreCase("Email")){
					accntPref.setEmail(contact.getContact());
				}
			}
		}
		else{
			accntPref = accntPrefList.get(0);
			prefs = accntPref.getPrefs();
			lang = accntPref.getLanguage();
			boolean notify = accntPref.isNotify();
		}
		
		
		for(Preference pref: prefs) {
			TemplateLink templateLink = getTemplateLink(eventType, lang, pref);
			Template t = ve.getTemplate("templates/"+templateLink.getTemplateName(), "UTF-8");
			
			StringWriter stringWriter = new StringWriter();
			t.merge(context, stringWriter);
			String notificaiton = stringWriter.toString();
			System.out.println(notificaiton);
			
			NotificationLog notifLog = new NotificationLog();
			notifLog.setAccountNumber(context.get("AccountNumber")+"");
			notifLog.setDate(new Date()+"");
			notifLog.setPref(pref);
			notifLog.setNotification(notificaiton);
			
			if(pref.equals(Preference.SMS)){
				notifLog.setCommunicationId(accntPref.getSms());
			} else if(pref.equals(Preference.EMAIL)){
				notifLog.setCommunicationId(accntPref.getEmail());
			} else {
				notifLog.setCommunicationId("INAPP");
			}
			
			invokeDispatcher(notifLog);
			notifLogRepo.save(notifLog);
		}

	}
	
	//Service to invoke dispatcher
	private void invokeDispatcher(NotificationLog log){
		String link = "http://104.131.44.187:8081/CxfRestService/rest/customerservices/getcustomeraccountdetails";
		Gson gson = new Gson();
		String data = gson.toJson(log);
		Client client = ClientBuilder.newClient(new ClientConfig());
		client.target(link).request(MediaType.APPLICATION_JSON).post(Entity.json(data));
	}

	
	public TemplateLink getTemplateLink(EventType eventType, Language lang, Preference pref){
		Query query = new Query();
		query.addCriteria(Criteria.where("eventType").is(eventType));
		query.addCriteria(Criteria.where("language").is(lang));
		query.addCriteria(Criteria.where("pref").is(pref));
		return template.find(query, TemplateLink.class).get(0);
		
	}
	


	public Map<String, Object> getCustAttributes() {
		return custAttributes;
	}

	public void setCustAttributes(Map<String, Object> custAttributes) {
		this.custAttributes = custAttributes;
	}

	public Map<String, Object> getDataAttributes() {
		return dataAttributes;
	}

	public void setDataAttributes(Map<String, Object> dataAttributes) {
		this.dataAttributes = dataAttributes;
	}

}
