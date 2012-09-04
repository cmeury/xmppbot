package de.raion.xmppbot.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * Marker Interface to annotate classes of type AbstractMessageListnerPlugin
 * for autoloading on startup
 * @see AbstractMessageListenerPlugin
  */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MessageListenerPlugin {

	/** shortname of the plugin **/
	String name();
	/** description of what the plugin is doing */
	String description();
}
