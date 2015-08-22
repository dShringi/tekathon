package dto;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private String id;
	private String accountCode;
	private String productId;
	private String productType;
	private String accountTypeId;
	private String accountType;
	private String balance;
	private String accountStatus;
	private String processedFlag;
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
	 * @return The accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}

	/**
	 * 
	 * @param accountCode
	 *            The accountCode
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * 
	 * @return The productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * 
	 * @param productId
	 *            The productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 
	 * @return The productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * 
	 * @param productType
	 *            The productType
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 
	 * @return The accountTypeId
	 */
	public String getAccountTypeId() {
		return accountTypeId;
	}

	/**
	 * 
	 * @param accountTypeId
	 *            The accountTypeId
	 */
	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	/**
	 * 
	 * @return The accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * 
	 * @param accountType
	 *            The accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * 
	 * @return The balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param balance
	 *            The balance
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 * 
	 * @return The accountStatus
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * 
	 * @param accountStatus
	 *            The accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * 
	 * @return The processedFlag
	 */
	public String getProcessedFlag() {
		return processedFlag;
	}

	/**
	 * 
	 * @param processedFlag
	 *            The processedFlag
	 */
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}