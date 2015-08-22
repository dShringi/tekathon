package dto;

import java.util.HashMap;
import java.util.Map;

public class IntRateChangeEvnt {
	String MessageId;
	String EventType;
	String EventCategory;
	String TDate;
	String EffectiveDate;
	Float  OldInterestRate;
	Float  NewInterestRate;
	String CustId;
	
	public String getCustId() {
		return CustId;
	}

	public void setCustId(String custId) {
		CustId = custId;
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

	public String getTDate() {
		return TDate;
	}

	public void setTDate(String tDate) {
		TDate = tDate;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}

	public Float getOldInterestRate() {
		return OldInterestRate;
	}

	public void setOldInterestRate(Float oldInterestRate) {
		OldInterestRate = oldInterestRate;
	}

	public Float getNewInterestRate() {
		return NewInterestRate;
	}

	public void setNewInterestRate(Float newInterestRate) {
		NewInterestRate = newInterestRate;
	}

	public Map<String,String> getMap()
	{
		Map<String,String> attributes = new HashMap<String, String>();
		attributes.put("MessageId", this.getMessageId());
		attributes.put("EventCategory", this.getEventCategory());
		attributes.put("EventType", this.getEventType());
		attributes.put("TransactionDate", this.getTDate());
		attributes.put("IntChangeEffectiveDt", this.getEffectiveDate());
		attributes.put("OldInterestRate",Float.toString(this.getOldInterestRate()));
		attributes.put("OldInterestRate",Float.toString(this.getNewInterestRate()));
		attributes.put("customerId",getCustId());

		return attributes;
	}

	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Event Type is : "+getEventType());
		builder.append(" ");
		builder.append("Event Category is : "+getEventCategory());
		builder.append(" ");
		builder.append("Old Interest Rate : "+getOldInterestRate());
		builder.append(" ");
		builder.append("New Interest Rate : "+getNewInterestRate());
		builder.append(" ");
		builder.append("Today is : "+getTDate());
		builder.append(" ");
		builder.append("Effective Date is : "+getEffectiveDate());
		builder.append(" ");

		return builder.toString();
	}

}
