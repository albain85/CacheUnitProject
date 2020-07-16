/*
package com.hit.server;

import java.net.Socket;

import com.hit.services.CacheUnitController;

public class HandleRequest<T> implements Runnable {

	public HandleRequest(Socket s, CacheUnitController<T> controller) {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
*/

package com.hit.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> extends Object implements Runnable {
	private CacheUnitController<T> cuc;
	private Scanner scanner;
	private PrintWriter pw;
	private Gson gson;
	private String cAddress;
	private int requests = 0 , numUpdates = 0 , numDelete = 0;
	public boolean processing;
	
	public HandleRequest(Socket s, CacheUnitController<T> controller) throws IOException {
		cAddress = s.getInetAddress().getHostAddress();
		scanner = new Scanner(s.getInputStream());
		pw = new PrintWriter(s.getOutputStream());
		gson = new Gson();
		cuc = controller;
		processing = true;
	}
	
	public void run() {
		Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
		
		while (processing) {
			String json;			
			try {
				processing = false;
				json = scanner.nextLine();
				processing = true;
			}
			catch (Exception e) {
				processing = false;
				return;
			}
			
			Request<DataModel<T>[]> request = gson.fromJson(json, ref);
			DataModel<T>[] requestBody = request.getBody();
			switch (request.getHeaders().get("action"))
			{
				case "GET" : {
					DataModel<T>[] dataModels = cuc.get(requestBody);
					pw.println(gson.toJson(dataModels));
					requests++;
					}
					break;
				case "UPDATE" :{ 
					if (cuc.update(requestBody)) {
						pw.println("Update Done!");
						numUpdates+=requestBody.length;
					}
					
					else {
						pw.println("Update Failed!");
					}
					requests++;
					break;
				}
				case "DELETE" :{ 
					if(cuc.delete(requestBody)) {
						pw.println("Deleted!");
						numDelete+=requestBody.length;
					}
					else {
						pw.println("Failed to delete!");
					}
					requests++;
					break;
				}
				case "serverStatus" : {
					pw.println("Your address is " + cAddress	+ ". Total number of requests: " + requests 
							+ ". Number of UPDATE DataModels : " + numUpdates + ". Number of DELETE DataModels : " + this.numDelete
							+ ". Used cache algo is LRU."); 
					break;
				}
			}
			pw.flush();
		}
	}
}
