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

public class HttpRequestExecuterFactory {
	
	static private Class defaultExecuter = null;
	
	public void setStaticDefaultType(Class defaultNew) {
		setDefaultType(defaultNew);
	}
	static public void setDefaultType(Class defaultNew) {
		defaultExecuter = defaultNew;
	}
	static public Class getDefaultType() {
		return defaultExecuter;
	}
	static public HttpRequestExecuter newDefault() {
		try {
			return (HttpRequestExecuter) defaultExecuter.newInstance();
		} catch( Exception ie ) {
			throw new RuntimeException(ie);
		}
	}
}
