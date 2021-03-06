/*
 ###############################################################################
 #                                                                             #
 #    Copyright (C) 2011-2012 OpenMEAP, Inc.                                   #
 #    Credits to Jonathan Schang & Robert Thacher                              #
 #                                                                             #
 #    Released under the LGPLv3                                                #
 #                                                                             #
 #    OpenMEAP is free software: you can redistribute it and/or modify         #
 #    it under the terms of the GNU Lesser General Public License as published #
 #    by the Free Software Foundation, either version 3 of the License, or     #
 #    (at your option) any later version.                                      #
 #                                                                             #
 #    OpenMEAP is distributed in the hope that it will be useful,              #
 #    but WITHOUT ANY WARRANTY; without even the implied warranty of           #
 #    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
 #    GNU Lesser General Public License for more details.                      #
 #                                                                             #
 #    You should have received a copy of the GNU Lesser General Public License #
 #    along with OpenMEAP.  If not, see <http://www.gnu.org/licenses/>.        #
 #                                                                             #
 ###############################################################################
 */

package com.openmeap.util;

import java.util.Map;

public interface HttpRequestExecuter {
	
	public final static String SSL_PEER_NOVERIFY_PROPERTY = "com.openmeap.sslPeerNoVerify";
	
	public HttpResponse postXml(String url, String xmlData)  throws HttpRequestException;
	public HttpResponse postData(String url, Map params) throws HttpRequestException;
	public HttpResponse postData(String url, Map urlParams, Map postParams) throws HttpRequestException;
	public HttpResponse get(String url) throws HttpRequestException;
	public HttpResponse get(String url, Map params) throws HttpRequestException;
	public void shutdown();
}
