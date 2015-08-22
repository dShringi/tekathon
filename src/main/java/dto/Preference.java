package dto;

import com.google.gson.annotations.SerializedName;

public enum Preference {

	@SerializedName("0") SMS(0),

	@SerializedName("1") EMAIL(1),

	@SerializedName("2") INAPP(2);

	private final int value;

	public int getValue() {
		return value;
	}

	private Preference(int value) {
		this.value = value;
	}
}
