package de.raion.xmppbot;
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

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.raion.xmppbot.context.XmppContext;

public class TestPacketListener extends AbstractPacketListener {
public TestPacketListener(XmppBot aXmppBot) {
		super(aXmppBot);
		// TODO Auto-generated constructor stub
	}

// static variables
/** default logger */
@SuppressWarnings("unused")
private static Logger log = LoggerFactory.getLogger(TestPacketListener.class);

@Override
public PacketFilter getAcceptFilter() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void processMessage(XmppContext xmppContext, Chat chat, Message message) {
	// TODO Auto-generated method stub

}

@Override
public void processMessage(XmppContext xmppContext, MultiUserChat muc, Message message) {
	// TODO Auto-generated method stub

}

}
