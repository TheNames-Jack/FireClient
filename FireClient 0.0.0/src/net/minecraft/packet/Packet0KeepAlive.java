package net.minecraft.packet;

import java.io.*;

import net.minecraft.client.network.NetHandler;

public class Packet0KeepAlive extends Packet {

	public int randomId;

	public Packet0KeepAlive() {
	}

	public Packet0KeepAlive(int i) {
		randomId = i;
	}

	public void processPacket(NetHandler nethandler) {
		nethandler.handleKeepAlive(this);
	}

	public void readPacketData(DataInputStream datainputstream) throws IOException {
		randomId = datainputstream.readInt();
	}

	public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
		dataoutputstream.writeInt(randomId);
	}

	public int getPacketSize() {
		return 4;
	}
}
