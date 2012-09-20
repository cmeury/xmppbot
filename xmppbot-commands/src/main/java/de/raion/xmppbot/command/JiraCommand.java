package de.raion.xmppbot.command;

import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.Parameter;

import de.raion.xmppbot.XmppContext;
import de.raion.xmppbot.plugin.JiraIssuePlugin;

@CLICommand(name="jira", description="configures the jira-issue plugin")
public class JiraCommand extends AbstractXmppCommand {

	private static Logger log = LoggerFactory.getLogger(JiraCommand.class);
	
	@Parameter(names = { "-d", "--domain" }, description = "sets the domain jira is running")
	String domain;
	
	@Override
	protected CommandResult innerExecute(XmppContext context) {
		if(domain != null) {
			setDomain(domain, context);
		}
		return CommandResult.OK;
	}

	private void setDomain(String aDomain, XmppContext context) {
		try {
			JiraConfig config = context.loadConfig(JiraConfig.class);
			config.setJiraDomain(aDomain);
			context.saveConfig(config);
			println("set jira domain to '"+domain+"'");
			
			if(context.getPluginManager().isEnabled(JiraIssuePlugin.class)) {
				JiraIssuePlugin plugin = context.getPluginManager().get(JiraIssuePlugin.class);
				plugin.init();
			}
			
			
		}catch(Exception e) {
			log.error("setDomain(String, XmppContext)", e);
			println("couldn't configure domain, error occured");
		}
		
		
	}

}
