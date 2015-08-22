package dto;

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

	public int getValue() {
		return value;
	}

	private EventType(int value) {
		this.value = value;
	}

}
