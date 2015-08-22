package mbeans;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;

import dao.EmailRepository;
import dao.SmsRepository;
import dao.WebRepository;

@ManagedBean(name = "createTemplateBean")
@SessionScoped
public class CreateTemplateBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message;
	private String prefrence;
	private String language;
	
//	private boolean email;
//	private boolean web;
//	private boolean sms;
	
	
	private List<String> prefrences;
	private List<String> prefrenceComponets;
//	private List<String> emailPref;
//	private List<String> smsPref;
//	private List<String> webPref;
	private List<String> languages;
	
	private SmsRepository smsRepository;
	private EmailRepository emailRepository;
	private WebRepository webRepository;

	public List<String> getPrefrences() {
		return prefrences;
	}

	public void setPrefrences(List<String> prefrences) {
		this.prefrences = prefrences;
	}

	public List<String> getPrefrenceComponets() {
		return prefrenceComponets;
	}

	public void setPrefrenceComponets(List<String> prefrenceComponets) {
		this.prefrenceComponets = prefrenceComponets;
	}
	
	

	@PostConstruct
	public void init() {
		setPrefrences(new ArrayList<>());
		getPrefrences().add("sms");
		getPrefrences().add("email");
		getPrefrences().add("web");
		
		setPrefrenceComponets(new ArrayList<>());
		getPrefrenceComponets().add("custId");
		getPrefrenceComponets().add("AcntNo");
		getPrefrenceComponets().add("Location");
		
//		setSmsPref(new ArrayList<>());
//		getSmsPref().add("Last Name");
//		
//		setWebPref(new ArrayList<>());
//		getWebPref().add("First Name");
//		
//		setEmailPref(new ArrayList<>());
//		getEmailPref().add("AccountType");
//		
		setLanguages(new ArrayList<>());
		getLanguages().add("English");
		getLanguages().add("French");
	}
	
	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPrefrence() {
		return prefrence;
	}

	public void setPrefrence(String prefrence) {
		this.prefrence = prefrence;
	}
	public String createTemplate() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("prefrences", this.prefrences);
		session.setAttribute("eventComponets", this.prefrenceComponets);
		session.setAttribute("languages", getLanguages());
		return "createTemplate";
	}
	
	public void submit() throws IOException {
		ClassPathResource resource = new ClassPathResource("templates\\cricInfo.vm");
		if(getPrefrence().equals("sms")){
			//smsRepository.save(new Sms(getPrefrence(),getLanguage()));
			try {
				URL url = resource.getURL();
				String fileName="\\sms.txt";
				PrintWriter writer = getWriter(url,fileName);
				writer.println("Hi,");
				writer.println(getMessage());
				writer.println("welcome to ATOM BANK");
				writer.close();
			} catch (URISyntaxException | FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
						
		}else if(getPrefrence().equals("web")){
			//webRepository.save(new Web(getPrefrence(),getLanguage()));
			try {
				URL url = resource.getURL();
				String fileName="\\web.txt";
				PrintWriter writer = getWriter(url,fileName);
				writer.println("Hi,");
				writer.println(getMessage());
				writer.println("welcome to ATOM BANK");
				writer.close();
			} catch (URISyntaxException | FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if(getPrefrence().equals("email")){
			//emailRepository.save(new Email(getPrefrence(),getLanguage()));
			try {
				URL url = resource.getURL();
				String fileName="\\email.txt";
				PrintWriter writer = getWriter(url,fileName);
				writer.println("Hi,");
				writer.println(getMessage());
				writer.println("welcome to ATOM BANK");
				writer.close();
			} catch (URISyntaxException | FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	private PrintWriter getWriter(URL url,String fileName) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException{
			File file = new File(url.toURI().getPath());
			PrintWriter writer = new PrintWriter(file.getParent()+fileName, "UTF-8");
			return writer;
			
	}

//	public boolean isEmail() {
//		return email;
//	}
//
//	public void setEmail(boolean email) {
//		this.email = email;
//	}
//
//
//	public boolean isWeb() {
//		return web;
//	}
//
//	public void setWeb(boolean web) {
//		this.web = web;
//	}
//
//	public boolean isSms() {
//		return sms;
//	}
//
//	public void setSms(boolean sms) {
//		this.sms = sms;
//	}

//	public List<String> getEmailPref() {
//		return emailPref;
//	}
//
//	public void setEmailPref(List<String> emailPref) {
//		this.emailPref = emailPref;
//	}
//
//	public List<String> getSmsPref() {
//		return smsPref;
//	}
//
//	public void setSmsPref(List<String> smsPref) {
//		this.smsPref = smsPref;
//	}
//
//	public List<String> getWebPref() {
//		return webPref;
//	}
//
//	public void setWebPref(List<String> webPref) {
//		this.webPref = webPref;
//	}
}
