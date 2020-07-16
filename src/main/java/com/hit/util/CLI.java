package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/*
package com.hit.util;

import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.hit.server.Server;

public class CLI implements Runnable{

	PropertyChangeListener pcl;
	InputStream in;
	OutputStream out;
	String command;
	
	public CLI(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcl = pcl;
		
	}
	
	void removePropertyChangeListener(PropertyChangeListener pcl) {
		this.pcl = pcl;
	}
	
	public void run() {
		
		Scanner sc = new Scanner(in);
		PrintWriter pw = new PrintWriter(out);
		Thread th = new Thread();
		
		command = sc.nextLine();
		while(!command.equals("shutdown")) {
		if(command.equals("start")) {
			System.out.println("Starting Server...");
			// TODO do some job to start server 
			th.start();
		}
		else
			if(command.equals("shutdown")) {
				System.out.println("Server shuting down...");
				
				break;
			}
			else
				System.out.println("Unknown command");
		
		command = sc.nextLine();
	}
		
	}
	void write(String string) {
		System.out.println(string);
	}
}
*/


public class CLI extends Object implements Runnable {
	
	private Scanner scanner;
	private PrintStream ps;
	private PropertyChangeSupport support;
		
	public CLI(InputStream in,OutputStream out) throws IOException {
		scanner = new Scanner(in);
		ps = new PrintStream(out);
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
	
	public void write(String string) {
		ps.println(string);
	}
	
	public void run() {
		String comamnd = "Available commands:\n start - to start the server \n shutdown - to terminate the server\nPlease type a command:";
		ps.println(comamnd);
		
		String input = scanner.nextLine(); 
		while (!input.equals("shutdown")) {
			if (input.equals("start")) {
				this.support.firePropertyChange("startServer", null, null);
			} 
			else { 
				ps.println("Unknown command."); 
				ps.println(comamnd);
				}
			
			input = scanner.nextLine();
		}
		
		support.firePropertyChange("shutdownServer", null, null);
	}
}


 