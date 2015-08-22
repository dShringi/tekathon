package dto;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public enum EventType {
	
	@SerializedName("0") ACCOUNTOPENED(0),
	@SerializedName("1") WITHDRAWAL(1),
	@SerializedName("2") FUNDED(2),
	@SerializedName("3") INTRATECHANGE(3),
	@SerializedName("4") UNDERSERVICE(4),
	@SerializedName("5") MULTIPLEWITHDRAWALS(5),
	@SerializedName("6") MINBALRCHD(6);

	private final int value;
	private final static Map<String, EventType> eventTypeMap = new HashMap<>();
	
	static{
		eventTypeMap.put("ACCOUNTOPENED", EventType.ACCOUNTOPENED);
		eventTypeMap.put("WITHDRAWAL", EventType.WITHDRAWAL);
		eventTypeMap.put("FUNDED", EventType.FUNDED);
		eventTypeMap.put("INTRATECHANGE", EventType.INTRATECHANGE);
		eventTypeMap.put("UNDERSERVICE", EventType.UNDERSERVICE);
		eventTypeMap.put("MULTIPLEWITHDRAWALS", EventType.MULTIPLEWITHDRAWALS);
		eventTypeMap.put("MINBALRCHD", EventType.MINBALRCHD);
	}

	public int getValue() {
		return value;
	}

	private EventType(int value) {
		this.value = value;
	}

	public static EventType getEventType(String eventType){
		return eventTypeMap.get(eventType);
	}
}
