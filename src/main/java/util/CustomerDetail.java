package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDetail {

	private String id;
	private String active;
	private String fname;
	private String lname;
	private List<Contact> contacts = new ArrayList<Contact>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * 
	 * @param active
	 *            The active
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * 
	 * @return The fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * 
	 * @param fname
	 *            The fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * 
	 * @return The lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * 
	 * @param lname
	 *            The lname
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * 
	 * @return The contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * 
	 * @param contacts
	 *            The contacts
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Map<String,Object> getMap()
	{
		Map<String,Object> attributes = new HashMap<String, Object>();
		attributes.put("id", this.getId());
		attributes.put("active", this.getActive());
		attributes.put("fname", this.getFname());
		attributes.put("lname", this.getLname());
		attributes.put("contacts", this.getContacts());
		return attributes;
	}
}