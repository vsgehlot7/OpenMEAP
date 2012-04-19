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

package com.openmeap.blackberry;

import java.io.IOException;
import java.util.Hashtable;

import org.json.me.JSONException;

import com.openmeap.digest.DigestInputStreamFactory;
import com.openmeap.thinclient.SLICConfig;
import com.openmeap.util.GenericRuntimeException;

import net.rim.device.api.ui.UiApplication;

import com.openmeap.blackberry.digest.Md5DigestInputStream;
import com.openmeap.blackberry.digest.Sha1DigestInputStream;

/**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 */
public class OpenMEAPApp extends UiApplication
{
	private SLICConfig config;
	
    /**
     * Creates a new MyApp object
     * @throws JSONException 
     * @throws IOException 
     */
    public OpenMEAPApp() {
    	
    	DigestInputStreamFactory.setDigestInputStreamForName("MD5", Md5DigestInputStream.class);
    	DigestInputStreamFactory.setDigestInputStreamForName("SHA1", Sha1DigestInputStream.class);
    	
    	try {
	    	config = new SLICConfig(
	    			new SharedPreferencesImpl("slic-config"),
	    			new Hashtable()
	    		);
	    } catch(JSONException jse) {
	    	throw new GenericRuntimeException(jse);
	    } catch(IOException ioe) {
	    	throw new GenericRuntimeException(ioe);
	    }
    	
        pushScreen(new OpenMEAPScreen());
    }    
}