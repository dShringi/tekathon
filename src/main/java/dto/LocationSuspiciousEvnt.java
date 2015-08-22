package dto;

public class LocationSuspiciousEvnt {
	String MessageId;
	String EventType;
	String EventCategory;
	String AccountNumber;
	String LocationOne;
	String TDateOne;
	Float  TAmtOne;

	String LocationTwo;
	String TDateTwo;
	Float  TAmtTwo;

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

	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}

	
	
	public String getLocationOne() {
		return LocationOne;
	}
	public void setLocationOne(String locationOne) {
		LocationOne = locationOne;
	}
	public String getTDateOne() {
		return TDateOne;
	}
	public void setTDateOne(String tDateOne) {
		TDateOne = tDateOne;
	}
	public Float getTAmtOne() {
		return TAmtOne;
	}
	public void setTAmtOne(Float tAmtOne) {
		TAmtOne = tAmtOne;
	}
	public String getLocationTwo() {
		return LocationTwo;
	}
	public void setLocationTwo(String locationTwo) {
		LocationTwo = locationTwo;
	}
	public String getTDateTwo() {
		return TDateTwo;
	}
	public void setTDateTwo(String tDateTwo) {
		TDateTwo = tDateTwo;
	}
	public Float getTAmtTwo() {
		return TAmtTwo;
	}
	public void setTAmtTwo(Float tAmtTwo) {
		TAmtTwo = tAmtTwo;
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
		builder.append("Location  one: "+getLocationOne());
		builder.append(" ");
		builder.append("Transaction one DateTime : "+getTDateOne());
		builder.append(" ");
		builder.append("Transaction one Amt : "+getTAmtOne());
		builder.append(" ");
		builder.append("Location two : "+getLocationTwo());
		builder.append(" ");
		builder.append("Transaction two DateTime : "+getTDateTwo());
		builder.append(" ");
		builder.append("Transaction two Amt : "+getTAmtTwo());
		builder.append(" ");
		return builder.toString();
	}

}
