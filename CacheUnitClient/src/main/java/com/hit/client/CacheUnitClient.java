package com.hit.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class CacheUnitClient {
	private InetAddress address;
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private String messegeFromServer = null;

	public CacheUnitClient() {

	}

	public String send(String request) {
		try {
			address = InetAddress.getByName("localhost");
			socket = new Socket(address, 12345);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());

			outputStream.writeObject(request);
			messegeFromServer = (String) inputStream.readObject();

			socket.close ();
			inputStream.close ();
			outputStream.close ();

		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return messegeFromServer;
	}
}
