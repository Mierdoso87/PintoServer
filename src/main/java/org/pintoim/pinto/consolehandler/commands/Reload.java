package org.pintoim.pinto.consolehandler.commands;

import org.pintoim.pinto.PintoServer;
import org.pintoim.pinto.consolehandler.ConsoleCaller;
import org.pintoim.pinto.consolehandler.ConsoleCommand;

public class Reload implements ConsoleCommand {
	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String getDescription() {
		return "Reloads the server configuration";
	}

	@Override
	public String getUsage() {
		return "reload";
	}

	@Override
	public int getMinArgsCount() {
		return 0;
	}

	@Override
	public int getMaxArgsCount() {
		return 0;
	}

	@Override
	public void execute(PintoServer server, ConsoleCaller caller, String[] args) throws Exception {
		PintoServer.logger.info("Reloading server configuration...");
		server.initConfig();
	}
}
