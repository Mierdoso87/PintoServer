package org.pintoim.pinto.consolehandler.commands;

import org.pintoim.pinto.PintoServer;
import org.pintoim.pinto.consolehandler.ConsoleCaller;
import org.pintoim.pinto.consolehandler.ConsoleCommand;

public class Unban implements ConsoleCommand {
	@Override
	public String getName() {
		return "unban";
	}

	@Override
	public String getDescription() {
		return "Unbans the specified user";
	}

	@Override
	public String getUsage() {
		return "ban <user>";
	}

	@Override
	public int getMinArgsCount() {
		return 1;
	}

	@Override
	public int getMaxArgsCount() {
		return 1;
	}

	@Override
	public void execute(PintoServer server, ConsoleCaller caller, String[] args) throws Exception {
		String target = args[0];
		server.unbanUser(target, false);
		caller.sendMessage("Unbanned the user " + target + "!");
	}
}
