package dto;

import java.util.HashMap;
import java.util.Map;

public class AmtSuspiciousEvnt {
	String MessageId;
	String EventType;
	String EventCategory;
	String AccountNumber;
	String TDate;	
	Float  TAmt;
	Float  ThresholdAmt;

	public String getTDate() {
		return TDate;
	}

	public void setTDate(String tDate) {
		TDate = tDate;
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

	public Float getTAmt() {
		return TAmt;
	}

	public void setTAmt(Float tAmt) {
		TAmt = tAmt;
	}

	public Float getThresholdAmt() {
		return ThresholdAmt;
	}

	public void setThresholdAmt(Float thresholdAmt) {
		ThresholdAmt = thresholdAmt;
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
		builder.append("Amount Withdrawn is : "+getTAmt());
		builder.append(" ");
		builder.append("Amount greater than : "+getThresholdAmt());
		builder.append(" ");
		return builder.toString();
	}

	public Map<String,String> getMap()
	{
		Map<String,String> attributes = new HashMap<String, String>();
		attributes.put("MessageId", this.getMessageId());
		attributes.put("AccountNumber", this.getAccountNumber());
		attributes.put("EventCategory", this.getEventCategory());
		attributes.put("EventType", this.getEventType());
		attributes.put("TransactionDate", this.getTDate());
		attributes.put("TransactionAmt",Float.toString(this.getTAmt()));
		attributes.put("Threshold",Float.toString(this.getThresholdAmt()));
		return attributes;
		
	}

	
}
