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
	
	
	private List<String> prefrences;
	private List<String> prefrenceComponets;
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
		prefrences=new ArrayList<>();
		prefrences.add("sms");
		prefrences.add("email");
		prefrences.add("web");
		
		prefrenceComponets=new ArrayList<>();
		prefrenceComponets.add("custId");
		prefrenceComponets.add("AcntNo");
		prefrenceComponets.add("Location");
		
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
				String fileName="\\web.txt";
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
}
