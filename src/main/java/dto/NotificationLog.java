package dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="log")
public class NotificationLog {

	@Id
	private String id;
	private String accountNumber;
	private Preference pref;
	private String notification;
	private String communicationId;
	private String date;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Preference getPref() {
		return pref;
	}
	public void setPref(Preference pref) {
		this.pref = pref;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getCommunicationId() {
		return communicationId;
	}
	public void setCommunicationId(String communicationId) {
		this.communicationId = communicationId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
