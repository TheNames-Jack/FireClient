package net.minecraft.packet;

import java.io.*;

import net.minecraft.client.network.NetHandler;

public class Packet201PlayerInfo extends Packet {
	public String username;
	public String uuid;
	public boolean isConnected;
	public int ping;

	public Packet201PlayerInfo() {
	}

	public void readPacketData(DataInputStream datainputstream) throws IOException {
		username = readString(datainputstream, 16);
		uuid = readString(datainputstream, 32);
		isConnected = datainputstream.readByte() != 0;
		ping = datainputstream.readShort();
	}

	public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
		writeString(username, dataoutputstream);
		writeString(uuid, dataoutputstream);
		dataoutputstream.writeByte(isConnected ? 1 : 0);
		dataoutputstream.writeShort(ping);
	}

	public void processPacket(NetHandler nethandler) {
		nethandler.handlePlayerInfo(this);
	}

	public int getPacketSize() {
		return 7 + username.length() + uuid.length();
	}
}