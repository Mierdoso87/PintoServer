package me.vlod.pinto.networking.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.vlod.pinto.networking.NetServerHandler;

public class PacketRegister extends Packet {
    public byte protocolVersion;
    public String clientVersion;
    public String name;
    public String passwordHash;
	
    public PacketRegister() { }
    
    public PacketRegister(byte protocolVersion, String name, String passwordHash) {
    	this.protocolVersion = protocolVersion;
    	this.name = name;
    	this.passwordHash = passwordHash;
    }
    
	@Override
	public void read(DataInputStream stream) throws IOException {
		this.protocolVersion = (byte) stream.read();
		this.clientVersion = Packet.readString(stream, 32);
		this.name = Packet.readString(stream, NetServerHandler.USERNAME_MAX);
		this.passwordHash = Packet.readString(stream, 64);
	}
	
	@Override
	public void write(DataOutputStream stream) throws IOException {
		stream.write(this.protocolVersion);
		Packet.writeString(stream, this.clientVersion, 32);
		Packet.writeString(stream, this.name, NetServerHandler.USERNAME_MAX);
		Packet.writeString(stream, this.passwordHash, 64);
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public int getPacketSize() {
		return 1 + 32 + NetServerHandler.USERNAME_MAX + 64;
	}
}
