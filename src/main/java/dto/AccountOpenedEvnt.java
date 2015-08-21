package dto;

public class AccountOpenedEvnt {
	String MessageId;
	String EventType;
	String EventCategory;
	String AccountNumber;
	String Location;
	String TDate;
	Float  BalanceAmt;
	
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
	public Float getBalanceAmt() {
		return BalanceAmt;
	}
	public void setBalanceAmt(Float tAmt) {
		BalanceAmt = tAmt;
	}
	
	public String getMessageId() {
		return MessageId;
	}
	public void setMessageId(String messageId) {
		MessageId = messageId;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
		builder.append("Transaction Date is : "+getTDate());
		builder.append(" ");
		builder.append("Balance : "+getBalanceAmt());
		builder.append(" ");
		builder.append("Transaction Location is : "+getLocation());
		builder.append(" ");

		return builder.toString();
	}

}
