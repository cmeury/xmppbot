package de.raion.xmppbot.filter;
/*
 * #%L
 * XmppBot Core
 * %%
 * Copyright (C) 2012 Bernd Kiefer
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.jivesoftware.smack.packet.Message;

import de.raion.xmppbot.context.XmppContext;

/**
 * Convinience Implementation of the MessageFilter Interface, providing Access
 * to the XmppContext
 *
 */
public abstract class AbstractMessageFilter implements MessageFilter {

	private XmppContext context;
	
	
	public XmppContext getContext() {
		return context;
	}


	public void setContext(XmppContext context) {
		this.context = context;
	}


	public abstract boolean accept(Message packet);

}
