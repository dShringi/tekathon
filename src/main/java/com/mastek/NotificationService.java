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

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.AccPrefRepository;
import dto.AccountPref;

@Path("rest")
@Component
//@Scope(value="request")
public class NotificationService {

    @Autowired
    private EventSource eventSrcService;

    @Autowired
	private AccPrefRepository acntPrefRepo;
    
    @Path("getAccountOpened")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccountOpened(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getAcctOpenedEvent(srcevent);
    }
    
    @Path("getAmtSuspiciousEvent")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAmtSuspiciousEvent(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getAmtSuspiciousEvent(srcevent);
    }

    @Path("getFundedEvent")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFundedEvent(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getFundedEvent(srcevent);
    }

    
    @Path("getIntRateChangeEvent")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getsrcevent(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getIntRateChangeEvent(srcevent);
    }

    
    @Path("getLocationSuspiciousEvent")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getLocationSuspiciousEvent(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getLocationSuspiciousEvent(srcevent);
    }

   
    @Path("getWithdrawnEvent")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWithdrawnEvent(@QueryParam("srcevent") String srcevent ) {
        return eventSrcService.getWithdrawnEvent(srcevent);
    }
    

    @GET
    @Path("/acctPref")
    @Produces(MediaType.TEXT_PLAIN)
	public Response getAcctPref(@Context HttpHeaders headers, @QueryParam("acctNo") String acctNo){
		List<AccountPref> acctPref = acntPrefRepo.findByAcctNo(acctNo);
		String result = acctPref.get(0).getEmail();
		return Response.status(201).entity(result).build();
	}

    
    
    
}
