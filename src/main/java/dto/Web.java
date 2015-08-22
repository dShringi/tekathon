package dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="web")
public class Web {
	@Id
	private String id;
	
	private String accNo;
	private String name;
	private String prefrence;
	private String language;
	
	
	public Web(String prefrence,String language){
		this.setPrefrence(prefrence);
		this.setLanguage(language);
	}
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPrefrence() {
		return prefrence;
	}

	public void setPrefrence(String prefrence) {
		this.prefrence = prefrence;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	

}
