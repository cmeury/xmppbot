package de.raion.xmppbot.plugin;


/**
 * notifies about the status of a plugin
 *
 */
public interface PluginStatusListener {

	/**
	 * called when the plugin manager disabled a plugin
	 * @param pluginName shortname
	 * @param plugin the plugin
	 */
	public <T> void pluginDisabled(String pluginName, AbstractMessageListenerPlugin<T> plugin);


	/**
	 * called when the plugin manager enabled a plugin
	 * @param pluginName shortname
	 * @param plugin the plugin
	 */
	public <T> void pluginEnabled(String pluginName, AbstractMessageListenerPlugin<T> plugin);
}
