package org.pintoim.pinto.consolehandler.commands;

import org.pintoim.pinto.PintoServer;
import org.pintoim.pinto.consolehandler.ConsoleCaller;
import org.pintoim.pinto.consolehandler.ConsoleCommand;
import org.pintoim.pinto.networking.NetServerHandler;

public class KickIP implements ConsoleCommand {
	@Override
	public String getName() {
		return "kick-ip";
	}

	@Override
	public String getDescription() {
		return "Kicks the specified IP address";
	}

	@Override
	public String getUsage() {
		return "kick-ip <user> <reason>";
	}

	@Override
	public int getMinArgsCount() {
		return 2;
	}

	@Override
	public int getMaxArgsCount() {
		return 2;
	}

	@Override
	public void execute(PintoServer server, ConsoleCaller caller, String[] args) throws Exception {
		String target = args[0];
		String reason = args[1];
		
		NetServerHandler[] handlers = server.getHandlersByAddress(target);
		for (NetServerHandler handler : handlers) {
			handler.kick("You have been kicked!\nReason: " + reason);
		}
		
		caller.sendMessage("Kicked the IP " + target + "!");
		PintoServer.logger.log("Moderation", "Kicked the IP \"" + target + "\" for \"" + reason + "\"");
	}
}
