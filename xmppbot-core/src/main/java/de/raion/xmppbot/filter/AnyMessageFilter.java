package de.raion.xmppbot.filter;

import org.jivesoftware.smack.packet.Message;

/**
 * Accepts every Message / Packet
 *
 */
public class AnyMessageFilter extends AbstractMessageFilter {
	
	@Override
	public boolean accept(Message packet) {
		return true;
	}
}
