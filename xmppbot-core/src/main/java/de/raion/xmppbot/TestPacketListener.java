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

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MultivaluedMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import de.raion.xmppbot.context.XmppContext;
import de.raion.xmppbot.filter.MessageBodyMatchesFilter;

public class TestPacketListener extends AbstractPacketListener {
	
	
	// static variables
	/** default logger */
		@SuppressWarnings("unused")
		private static Logger log = LoggerFactory.getLogger(TestPacketListener.class);
	
	 /*
	 * took it from
	 * http://stackoverflow.com/questions/163360/regular-expresion-to-match-urls-java
	 * and modified it
	 */
	private String regex = "(https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
	
	private Pattern pattern = Pattern.compile(regex);
	
	
	public TestPacketListener(XmppBot aXmppBot) {
		super(aXmppBot);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public PacketFilter getAcceptFilter() {
		
		
		return new MessageBodyMatchesFilter(regex);
	}

	@Override
	public void processMessage(XmppContext xmppContext, Chat chat,	Message message) {
		processMessage(xmppContext, message);

	}

	@Override
	public void processMessage(XmppContext xmppContext, MultiUserChat muc,Message message) {
		processMessage(xmppContext, message);

	}



	private void processMessage(XmppContext xmppContext, Message message) {
		
		Matcher matcher = pattern.matcher(message.getBody());
		
		while(matcher.find()) {
			String url = matcher.group();
			
			try {
			
				Document doc = Jsoup.connect(url).get();
				String title = doc.select("title").first().text();
				
				
				String meta  = doc.select("meta[name=description]").attr("content");
				//String meta = e.attr("content");
				
				StringBuilder builder = new StringBuilder(title).append(" - ").append(meta);
				
				xmppContext.println(builder.toString());
				
			
			} catch (IOException e) {
				log.error("processMessage(XmppContext, Message) - {}", e.getMessage());
			}
			
			
			
		}
		
	}

}
