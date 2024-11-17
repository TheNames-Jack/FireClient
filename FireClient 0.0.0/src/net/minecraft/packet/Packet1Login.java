package net.minecraft.packet;

import java.io.*;

import net.minecraft.client.network.NetHandler;

public class Packet1Login extends Packet {
	public int protocolVersion;
	public String username;
	public long mapSeed;
	public int serverMode;
	public byte worldType;
	public byte difficultySetting;
	public byte worldHeight;
	public byte maxPlayers;
	public String playerUUID;

	public Packet1Login() {
	}

	public Packet1Login(String username, int protocolVersion, String playerUUID) {
		this.username = username;
		this.protocolVersion = protocolVersion;
		if(playerUUID != null) {
			this.playerUUID = playerUUID;
		}else {
			this.playerUUID = "";
		}
	}

	public void readPacketData(DataInputStream datainputstream) throws IOException {
		protocolVersion = datainputstream.readInt();
		username = readString(datainputstream, 16);
		mapSeed = datainputstream.readLong();
		serverMode = datainputstream.readInt();
		worldType = datainputstream.readByte();
		difficultySetting = datainputstream.readByte();
		worldHeight = datainputstream.readByte();
		maxPlayers = datainputstream.readByte();
		playerUUID = readString(datainputstream, 32);
	}

	public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
		dataoutputstream.writeInt(protocolVersion);
		writeString(username, dataoutputstream);
		dataoutputstream.writeLong(mapSeed);
		dataoutputstream.writeInt(serverMode);
		dataoutputstream.writeByte(worldType);
		dataoutputstream.writeByte(difficultySetting);
		dataoutputstream.writeByte(worldHeight);
		dataoutputstream.writeByte(maxPlayers);
		writeString(playerUUID, dataoutputstream);
	}

	public void processPacket(NetHandler nethandler) {
		nethandler.handleLogin(this);
	}

	public int getPacketSize() {
		return 20 + username.length() + playerUUID.length();
	}
}
