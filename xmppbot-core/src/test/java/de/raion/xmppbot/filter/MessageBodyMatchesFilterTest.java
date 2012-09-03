package de.raion.xmppbot.filter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.regex.PatternSyntaxException;

import org.jivesoftware.smack.packet.Message;
import org.junit.Test;


public class MessageBodyMatchesFilterTest {

	@Test(expected = PatternSyntaxException.class)
	public void shouldThrowPatternSyntaxExceptionByNullArgument() {
		new MessageBodyMatchesFilter(null);
	}

	@Test(expected = PatternSyntaxException.class)
	public void shouldThrowPatternSyntaxExceptionByInvalidArgument() {
		String invalidRegex = "(a"; // right parenthesis missing
		new MessageBodyMatchesFilter(invalidRegex);
	}
	
	@Test
	public void shouldNotMatchWhenMessageHasNoBody() {
				
		// given
		Message message = new Message();
		MessageBodyMatchesFilter filter = new MessageBodyMatchesFilter("regex");
		assertFalse(filter.accept(message));
		
	}

	@Test
	public void shouldMatchUrl() {

		// given

		/*
		 * took it from
		 * http://stackoverflow.com/questions/163360/regular-expresion
		 * -to-match-urls-java and modified
		 */
		String regex = "(https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";

		Message message = new Message();
		message.setBody("a text with a link http://stackoverflow.com/questions/163360/regular-expresion-to-match-urls-java inside");

		MessageBodyMatchesFilter filter = new MessageBodyMatchesFilter(regex);

		assertTrue(filter.accept(message));
		
		message = new Message();
		message.setBody("http://dict.leo.org/ende?lp=ende&p=DOKJAA&search=parenthesis&trestr=0x8040");
		
		assertTrue(filter.accept(message));
	}
}
