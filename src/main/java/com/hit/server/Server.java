package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

public class Server implements PropertyChangeListener, Runnable {

		private ServerSocket server;
		private Thread serverThread;
		private boolean isServerRunning = false;
		private ArrayList<Socket> sockets;
		private ArrayList<HandleRequest> requests;
	
	public Server() throws Exception {
		this.sockets = new ArrayList<Socket>();
		this.requests = new ArrayList<HandleRequest>();
	}
	
	@Override
	public void run() {
		try {
			this.server = new ServerSocket(12345);
			
			while (true) {
				Socket socket = this.server.accept();
				this.sockets.add(socket);
				CacheUnitController<String> controller = new CacheUnitController<String>();
				HandleRequest<String> hr = new HandleRequest<String>(socket, controller);
				this.requests.add(hr);
				new Thread(hr).start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}

	@Override
	public void propertyChange(java.beans.PropertyChangeEvent evt) {
		CLI cli = ((CLI)evt.getSource());
		
		if (evt.getPropertyName().equals("startServer")) {
			if (isServerRunning) {
				cli.write("Server is running already.");
			} else {
				cli.write("Starting server...");
				serverThread = new Thread(this);
				serverThread.start();
				isServerRunning = true;
			  cli.write("Server has been started.");
			}		
		} else if (evt.getPropertyName().equals("shutdownServer")) {
			cli.write("Terminating server...");
			cli.write("Waiting for existing requests completion...");
			for (HandleRequest<String> request : requests)
			{
				while(request.processing);
			}
			cli.write("All requests completed.");
			for (Socket socket : this.sockets)
			{
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
			sockets.clear();
			requests.clear();
			isServerRunning = false;
		}
	}
}


