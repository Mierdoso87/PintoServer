package org.pintoim.pinto.consolehandler.commands;

import org.pintoim.pinto.PintoServer;
import org.pintoim.pinto.consolehandler.ConsoleCaller;
import org.pintoim.pinto.consolehandler.ConsoleCommand;

public class Stop implements ConsoleCommand {
	@Override
	public String getName() {
		return "stop";
	}

	@Override
	public String getDescription() {
		return "Stops the server, and kicks everyone with an optional reason";
	}

	@Override
	public String getUsage() {
		return "stop [reason]";
	}

	@Override
	public int getMinArgsCount() {
		return 0;
	}

	@Override
	public int getMaxArgsCount() {
		return 1;
	}

	@Override
	public void execute(PintoServer server, ConsoleCaller caller, String[] args) throws Exception {
		String reason = args.length > 0 ? args[0] : null;
		
		if (reason == null) {
			server.stop();
		} else {
			server.stop(reason);
		}
	}
}
