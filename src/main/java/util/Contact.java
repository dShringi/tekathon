package util;

import java.util.HashMap;
import java.util.Map;

public class Contact {

	private String contactType;
	private String contact;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType
	 *            The contactType
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return The contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            The contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}