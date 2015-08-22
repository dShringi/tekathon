package dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "actpref")
public class AccountPref {
	@Id
	private String id;

	private String acctNo;
	private boolean notify;
	private String sms;
	private String email;
	private Language language;
	private List<Preference> prefs;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	@Override
	public String toString() {
		return String.format("AccountPref[id=%s, notify='%s', sms='%s', email='%s', language='%s', acctNo='%s']", 
				id, notify, sms, email, language, acctNo);
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public List<Preference> getPrefs() {
		return prefs;
	}

	public void setPrefs(List<Preference> prefs) {
		this.prefs = prefs;
	}

}
