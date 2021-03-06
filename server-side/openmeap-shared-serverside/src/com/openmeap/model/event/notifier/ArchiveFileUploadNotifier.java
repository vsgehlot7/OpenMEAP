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

package com.openmeap.model.event.notifier;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openmeap.cluster.ClusterNotificationException;
import com.openmeap.constants.UrlParamConstants;
import com.openmeap.event.Event;
import com.openmeap.event.MessagesEvent;
import com.openmeap.event.ProcessingEvent;
import com.openmeap.model.ModelEntity;
import com.openmeap.model.ModelServiceOperation;
import com.openmeap.model.dto.ApplicationArchive;
import com.openmeap.model.event.ModelEntityEventAction;

public class ArchiveFileUploadNotifier extends AbstractArchiveEventNotifier {	
	
	private Logger logger = LoggerFactory.getLogger(ArchiveFileUploadNotifier.class);
	
	@Override
	protected String getEventActionName() {
		return ModelEntityEventAction.ARCHIVE_UPLOAD.getActionName();
	}
	
	@Override
	protected void addRequestParameters(ModelEntity modelEntity, Map<String,Object> parms) {
		ApplicationArchive archive = (ApplicationArchive)modelEntity;
		parms.put(UrlParamConstants.APPARCH_FILE, archive.getFile(getModelManager().getGlobalSettings().getTemporaryStoragePath()));
		super.addRequestParameters(modelEntity, parms);
	}
	
	@Override
	public Boolean notifiesFor(ModelServiceOperation operation, ModelEntity payload) {
		if(operation==ModelServiceOperation.SAVE_OR_UPDATE && ApplicationArchive.class.isAssignableFrom(payload.getClass()) ) {
			return true;
		}
		return false;
	}
	
	@Override
	public <E extends Event<ApplicationArchive>> void notify(final E event, List<ProcessingEvent> events) throws ClusterNotificationException {
		ApplicationArchive archive = (ApplicationArchive)event.getPayload();
		File archiveFile = archive.getFile(getModelManager().getGlobalSettings().getTemporaryStoragePath());
		if( !archiveFile.exists() ) {
			String msg = String.format("The archive file %s cannot be found.  This could be because you opted to fill in the version details yourself.",archiveFile.getAbsoluteFile());
			logger.warn(msg);
			events.add(new MessagesEvent(msg));
			return;
		}
		super.notify(event, events);
	}
}
