package de.raion.xmppbot.plugin;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

import de.raion.xmppbot.XmppBot;
import de.raion.xmppbot.XmppContext;

public class JiraIssuePlugin extends AbstractMessageListenerPlugin<JiraIssuePlugin> {

	public JiraIssuePlugin(XmppBot aXmppBot) {
		super(aXmppBot);
	}

	@Override
	public PacketFilter getAcceptFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processMessage(XmppContext xmppContext, Chat chat,
			Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processMessage(XmppContext xmppContext, MultiUserChat muc,
			Message message) {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}
