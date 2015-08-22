package dto;

import java.util.HashMap;
import java.util.Map;

public class SourceEvent {
	String MessageId;
	String EventType;
	String EventCategory;
	String AccountNumber;
	String TDate;
	Float  TAmt;
	String customerId;
	String teamId;
	
	
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getMessageId() {
		return MessageId;
	}

	public void setMessageId(String messageId) {
		MessageId = messageId;
	}

	public String getEventType() {
		return EventType;
	}
	public void setEventType(String eventType) {
		EventType = eventType;
	}
	public String getEventCategory() {
		return EventCategory;
	}
	public void setEventCategory(String eventCategory) {
		EventCategory = eventCategory;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getTDate() {
		return TDate;
	}
	public void setTDate(String tDate) {
		TDate = tDate;
	}
	public Float getTAmt() {
		return TAmt;
	}
	public void setTAmt(Float tAmt) {
		TAmt = tAmt;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Event Type is : "+getEventType());
		builder.append(" ");
		builder.append("Event Category is : "+getEventCategory());
		builder.append(" ");
		builder.append("Account Number is : "+getAccountNumber());
		builder.append(" ");
		builder.append("Transaction Data is : "+getTDate());
		builder.append(" ");
		builder.append("Transaction Amount is : "+getTAmt());
		builder.append(" ");
		return builder.toString();
	}

	public Map<String,Object> getMap()
	{
		Map<String,Object> attributes = new HashMap<String, Object>();
		attributes.put("MessageId", this.getMessageId());
		attributes.put("AccountNumber", this.getAccountNumber());
		attributes.put("EventCategory", this.getEventCategory());
		attributes.put("EventType", this.getEventType());
		attributes.put("TransactionDate", this.getTDate());
		attributes.put("TransactionAmt",Float.toString(this.getTAmt()));
		attributes.put("customerId",this.getCustomerId());
		attributes.put("teamId",this.getTeamId());
		return attributes;
		
	}


}
