/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.mastek;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.AccPrefRepository;
import dto.Account;
import dto.AccountDetail;
import dto.AccountOpenedEvnt;
import dto.AccountPref;
import dto.AmtSuspiciousEvnt;
import dto.IntRateChangeEvnt;
import dto.LocationSuspiciousEvnt;
import dto.NotificationLog;
import dto.SourceEvent;
import dto.TransactionEvnt;
import util.Contact;
import util.CustomerDetail;
import util.GenerateNotification;

/**
 * serviceimplementation to get source events
 * 
 * @author vinaya
 */

@Component("eventSource")
public class EventSourceImpl implements EventSource {

	@Autowired
	public GenerateNotification generateNotification;

	@Autowired
	public AccPrefRepository acntPrefRepo;

	@Override
	public String getevent(String srcevent) {

		Gson gson = new Gson();

		SourceEvent srcEvnt = gson.fromJson(srcevent, SourceEvent.class);

		System.out.println(srcEvnt.getEventCategory());
		System.out.println(srcEvnt.getEventType());
		System.out.println(srcEvnt.getAccountNumber());
		System.out.println(srcEvnt.getTDate());
		System.out.println(srcEvnt.getTAmt());

		return srcEvnt.toString();
	}

	@Override
	public String getAcctOpenedEvent(String srcevent) {
		Gson gson = new Gson();
		// Load Event payload
		AccountOpenedEvnt srcEvnt = gson.fromJson(srcevent, AccountOpenedEvnt.class);

		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);

		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());

		List<Account> accounts = accountDetail.getAccounts();
		for (Account account : accounts) {
			if (account.getAccountCode().equals(srcEvnt.getAccountNumber())) {
				Map<String, Object> dataAttributes = generateNotification.getDataAttributes();
				dataAttributes.put("productType", account.getProductType());
				dataAttributes.put("accountType", account.getAccountType());
				generateNotification.setDataAttributes(dataAttributes);
			}
		}

		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}

		System.out.println("Account Opened notification done");
		return srcEvnt.toString();
	}

	@Override
	public String getFundedEvent(String srcevent) {
		Gson gson = new Gson();

		// Load Event payload
		TransactionEvnt srcEvnt = gson.fromJson(srcevent, TransactionEvnt.class);
		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);
		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());

		List<Account> accounts = accountDetail.getAccounts();
		for (Account account : accounts) {
			if (account.getAccountCode().equals(srcEvnt.getAccountNumber())) {
				Map<String, Object> dataAttributes = generateNotification.getDataAttributes();
				dataAttributes.put("productType", account.getProductType());
				dataAttributes.put("accountType", account.getAccountType());
				generateNotification.setDataAttributes(dataAttributes);
			}
		}

		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}

		System.out.println("Account funded notification done");
		return srcEvnt.toString();
	}

	@Override
	public String getWithdrawnEvent(String srcevent) {
		Gson gson = new Gson();
		TransactionEvnt srcEvnt = gson.fromJson(srcevent, TransactionEvnt.class);
		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);

		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());
		List<Account> accounts = accountDetail.getAccounts();
		for (Account account : accounts) {
			if (account.getAccountCode().equals(srcEvnt.getAccountNumber())) {
				Map<String, Object> dataAttributes = generateNotification.getDataAttributes();
				dataAttributes.put("productType", account.getProductType());
				dataAttributes.put("accountType", account.getAccountType());
				generateNotification.setDataAttributes(dataAttributes);
			}
		}

		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}
		return srcEvnt.toString();
	}

	@Override
	public String getIntRateChangeEvent(String srcevent) {
		Gson gson = new Gson();
		IntRateChangeEvnt srcEvnt = gson.fromJson(srcevent, IntRateChangeEvnt.class);
		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);

		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());
		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}
		System.out.println("Interest Rate change notification done");
		return srcEvnt.toString();
	}

	@Override
	public String getLocationSuspiciousEvent(String srcevent) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		LocationSuspiciousEvnt srcEvnt = gson.fromJson(srcevent, LocationSuspiciousEvnt.class);

		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());

		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);

		List<Account> accounts = accountDetail.getAccounts();
		for (Account account : accounts) {
			if (account.getAccountCode().equals(srcEvnt.getAccountNumber())) {
				Map<String, Object> dataAttributes = generateNotification.getDataAttributes();
				dataAttributes.put("productType", account.getProductType());
				dataAttributes.put("accountType", account.getAccountType());
				generateNotification.setDataAttributes(dataAttributes);
			}
		}

		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}
		System.out.println("Location Suspicious notification done");

		return srcEvnt.toString();
	}

	@Override
	public String getAmtSuspiciousEvent(String srcevent) {
		Gson gson = new Gson();
		AmtSuspiciousEvnt srcEvnt = gson.fromJson(srcevent, AmtSuspiciousEvnt.class);

		// get user details
		String result = getUserDetail(srcEvnt.getCustomerId());
		CustomerDetail customerDetail = gson.fromJson(result, CustomerDetail.class);

		// generate notification component to populate evnt and customer details
		generateNotification.setDataAttributes(srcEvnt.getMap());
		generateNotification.setCustAttributes(customerDetail.getMap());
		String acctDetail = getOtherDetails(srcEvnt.getCustomerId());
		AccountDetail accountDetail = gson.fromJson(acctDetail, AccountDetail.class);

		List<Account> accounts = accountDetail.getAccounts();
		for (Account account : accounts) {
			if (account.getAccountCode().equals(srcEvnt.getAccountNumber())) {
				Map<String, Object> dataAttributes = generateNotification.getDataAttributes();
				dataAttributes.put("productType", account.getProductType());
				dataAttributes.put("accountType", account.getAccountType());
				generateNotification.setDataAttributes(dataAttributes);
			}
		}

		try {
			generateNotification.generateNotification();
		} catch (IOException e) {
			System.out.println("exception rais");
			e.printStackTrace();
		}
		System.out.println("Amount Suspicious notification done");
		return srcEvnt.toString();
	}

	// Service to get Customer Details for notification.
	private String getUserDetail(String custid) {
		String link = "http://104.131.44.187:8081/CxfRestService/rest/customerservices/getcustomerdetails";
		Client client = ClientBuilder.newClient(new ClientConfig());
		String userDetail = client.target(link).queryParam("customerId", custid)
				.request(MediaType.APPLICATION_JSON).get(String.class);
		return userDetail;
	}

	// Service to get Customer Details for notification.
	private String getOtherDetails(String custid) {
		String link = "http://104.131.44.187:8081/CxfRestService/rest/customerservices/getcustomeraccountdetails";
		Client client = ClientBuilder.newClient(new ClientConfig());
		String otherDetail = client.target(link).queryParam("customerId", custid)
				.request(MediaType.APPLICATION_JSON).get(String.class);
		return otherDetail;
	}

}
