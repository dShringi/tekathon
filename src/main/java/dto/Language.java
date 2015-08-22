package dto;

import com.google.gson.annotations.SerializedName;

public enum Language {
	
	@SerializedName("0")
	ENGLISH(0), 
	
	@SerializedName("1")
	FRENCH(1);
 
 private final int value;
 public int getValue() {
     return value;
 }

 private Language(int value) {
     this.value = value;
 }
}
