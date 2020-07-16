/*package com.hit.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.hit.dm.DataModel;

public class DaoFileImpl<T> extends Object implements IDao<Long,DataModel<T>> {

	String path;	
	int capacity;
	FileOutputStream fos = null;
	HashMap<Long,DataModel<T>> map = new HashMap<Long,DataModel<T>>();
	
	public DaoFileImpl(String filePath) {
		this.path = filePath;
	}
	
	DaoFileImpl(String filePath, int capacity){
		this.path = filePath;
		this.capacity = capacity;
	}
	
	@Override
	public void delete(DataModel<T> entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DataModel<T> find(Long id) {
		DataModel<T> dm = null;
		try {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = null;
		try {
		do {
			obj = ois.readObject();
			if(obj.equals(id)) {
				dm = (DataModel<T>) obj;
				break;
			}
		}
		while(obj != null); //must find end of file
		} catch(EOFException eof) {
			System.out.println("not found");
		}
		} catch(Exception ex) {
			
			ex.printStackTrace();
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dm;
	}

	@Override
	public void save(DataModel<T> entity) {
		try {

			fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(entity);

			System.out.println("Done");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
*/
package com.hit.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.hit.dm.DataModel;



public  class DaoFileImpl<T> implements  IDao<Long,DataModel<T>>  {
	public HashMap <Long,DataModel<T>> map = new HashMap<Long,DataModel<T>>();
	int capacity;
	private String path;
	private ObjectOutputStream out;
	private ObjectInputStream in;


	public DaoFileImpl(String filePath) {
		this.path = filePath;

	}
	
	@SuppressWarnings("unchecked")
	public DaoFileImpl(String filePath,int capacity) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.capacity = capacity;
		this.path = filePath;
		File file = new File(filePath);
		if(!file.exists()){
		file.createNewFile();
		}
		else 
			if(!(file.length() == 0)) { 
		in = new ObjectInputStream(new FileInputStream(path));
		map = (HashMap<Long,DataModel<T>>) in.readObject();  
		}
	}
	/*	public DaoFileImpl(String filePath,int capacity) throws FileNotFoundException, IOException, ClassNotFoundException {
		this.capacity = capacity;
		this.path = filePath;
		File file = new File(filePath);
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			map = (HashMap<Long,DataModel<T>>) in.readObject();  
		} catch(FileNotFoundException fnf) {
			file.createNewFile();
		}
		catch(IOException TheFileIsEmpty) {
			TheFileIsEmpty.printStackTrace();
			System.out.println(TheFileIsEmpty);
		}
	} */

	@Override
	public void delete(DataModel<T> entity) {
		
		if( map.containsKey(entity.getDataModelId()))	
		{
			map.remove(entity.getDataModelId());
	
		}
	}
	
	@Override
	public DataModel<T> find(Long id) {
		if (map.containsKey(id) ) 
			return map.get(id);
		else
			return null;
	}

	@Override
	public void save(DataModel<T> entity) {
		try {
			out= new ObjectOutputStream(new FileOutputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.writeObject(map);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
}