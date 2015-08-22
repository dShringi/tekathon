package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="accountDetail")
public class AccountDetail {

	private String customerId;
	private List<Account> accounts = new ArrayList<Account>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 *            The customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return The accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * 
	 * @param accounts
	 *            The accounts
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
