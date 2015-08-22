package dto;

public class SourceEvent {
	String EventType;
	String EventCategory;
	String AccountNumber;
	String TDate;
	Float  TAmt;
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

}
