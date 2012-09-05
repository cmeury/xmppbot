package de.raion.xmppbot.command;
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

import java.util.Map;
import java.util.Set;

import net.dharwin.common.tools.cli.api.CommandResult;
import net.dharwin.common.tools.cli.api.annotations.CLICommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.Parameter;

import de.raion.xmppbot.XmppContext;

/**
 *
 *
 */
@CLICommand(name = "plugin", description = "for managing available plugins")
public class PluginCommand extends AbstractXmppCommand {

	/** default logger */
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(PluginCommand.class);

	@Parameter(names = { "-l", "-list" }, description = "shows all available commands")
	boolean showList = false;

	@Override
	protected CommandResult innerExecute(XmppContext context) {

		if(showList) {
			printPluginList(context);
		}

		return CommandResult.OK;

	}

	private void printPluginList(XmppContext context) {

		Map<String, Boolean> statusMap = context.getPluginManager().getStatusMap();
		Set<String> keySet = statusMap.keySet();

		if(statusMap.size() == 0) {
			println("no plugins available!");
			return;
		}


		StringBuilder builder = new StringBuilder();

		for (String key : keySet) {

			builder.append(key).append("  - ");
			Boolean enabled = statusMap.get(key);
			if(enabled) {
				builder.append("enabled");
			} else {
				builder.append("disabled");
			}
			builder.append("\n");
		}
		println(builder.toString());
	}
}
