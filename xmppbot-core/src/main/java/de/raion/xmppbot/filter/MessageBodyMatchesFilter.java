package de.raion.xmppbot.filter;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.jivesoftware.smack.packet.Message;

public class MessageBodyMatchesFilter extends AbstractMessageFilter {

	private Pattern pattern;

	/**
	 * @param regex
	 *            the pattern to use
	 * @throws PatternSyntaxException
	 *             if the expressions syntax is invalid
	 */
	public MessageBodyMatchesFilter(String regex) throws PatternSyntaxException {
		try {
			pattern = Pattern.compile(regex);
		} catch (NullPointerException e) {
			throw new PatternSyntaxException("null is not a valid regex", null,
					-1);
		}
	}

	@Override
	public boolean accept(Message aMessage) {

		String body = aMessage.getBody();

		if (body == null || body.equals("")) {
			return false;
		}

		return pattern.matcher(body).find();
	}

}
