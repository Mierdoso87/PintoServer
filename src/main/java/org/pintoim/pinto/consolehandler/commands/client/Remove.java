package org.pintoim.pinto.consolehandler.commands.client;

import org.pintoim.pinto.Coloring;
import org.pintoim.pinto.GroupDatabaseEntry;
import org.pintoim.pinto.PintoServer;
import org.pintoim.pinto.UserDatabaseEntry;
import org.pintoim.pinto.consolehandler.ConsoleCaller;
import org.pintoim.pinto.consolehandler.ConsoleCommand;
import org.pintoim.pinto.networking.NetServerHandler;
import org.pintoim.pinto.networking.packet.PacketRemoveContact;

public class Remove implements ConsoleCommand {
	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public String getDescription() {
		return "Removes an user from this group chat";
	}

	@Override
	public String getUsage() {
		return "remove <name>";
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
		String groupID = caller.clientChat;
		String user = args[0];
		GroupDatabaseEntry groupDatabaseEntry = new GroupDatabaseEntry(server, groupID);
		groupDatabaseEntry.load();
		NetServerHandler userClient = server.getHandlerByName(user);
		
		if (!groupDatabaseEntry.members.contains(user)) {
			caller.sendMessage(Coloring.translateAlternativeColoringCodes(
					String.format("&8[&5!&8]&4 %s is not in the group", user)));
			return;
		}
		
		UserDatabaseEntry userDatabaseEntry = new UserDatabaseEntry(server, user);
		userDatabaseEntry.load();
		
		groupDatabaseEntry.members.remove(user);
		groupDatabaseEntry.save();
		userDatabaseEntry.contacts.remove(groupID);
		userDatabaseEntry.save();
		
		if (userClient != null) {
			userClient.databaseEntry.load();
			userClient.sendPacket(new PacketRemoveContact(groupID));	
		}
		caller.sendMessage(Coloring.translateAlternativeColoringCodes(
				String.format("&8[&5i&8]&4 You have removed %s from this group", user)));
		server.sendMessageInGroup(groupID, "", Coloring.translateAlternativeColoringCodes(
				String.format("&8[&5i&8]&4 %s has removed %s from this group", 
						caller.client.userName, user)));
	}
}
