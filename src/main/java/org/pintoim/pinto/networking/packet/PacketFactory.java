package org.pintoim.pinto.networking.packet;

import java.util.HashMap;

import org.pintoim.pinto.PintoServer;

public class PacketFactory {
    private static HashMap<Integer, Class<? extends Packet>> packetMap = 
    		new HashMap<Integer, Class<? extends Packet>>();

    static {
    	packetMap.put(0, PacketLogin.class);
    	packetMap.put(1, PacketRegister.class);
    	packetMap.put(2, PacketLogout.class);
    	packetMap.put(3, PacketMessage.class);
    	packetMap.put(4, PacketNotification.class);
    	packetMap.put(6, PacketAddContact.class);
    	packetMap.put(7, PacketRemoveContact.class);
    	packetMap.put(8, PacketStatus.class);
    	packetMap.put(9, PacketContactRequest.class);
    	packetMap.put(10, PacketClearContacts.class);
    	packetMap.put(11, PacketCallChangeStatus.class);
    	packetMap.put(17, PacketServerInfo.class);
    	packetMap.put(18, PacketTyping.class);
    	packetMap.put(255, PacketKeepAlive.class);
    }

    public static Packet getPacketByID(int id) {
    	// Get the packet class by ID
        Class<? extends Packet> packetClass = packetMap.get(id);

        // Check if the specified ID map was successful
        if (packetClass != null) {
    		try {
				return (Packet)packetClass.getDeclaredConstructor().newInstance();
			} catch (Exception ex) {
				// Failure?
				PintoServer.logger.throwable(ex);
			}
        }
        
        return null;
    }
}
