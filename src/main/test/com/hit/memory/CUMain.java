package com.hit.memory;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;

public class CUMain {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		DataModel<Integer> dm = new DataModel<Integer>((long) 1,131224);
		DataModel<Integer> dm1 = new DataModel<Integer>((long) 2,234124);
		DaoFileImpl<Integer> dao = new DaoFileImpl<Integer>("datasource.txt" , 100);
		
		
		dao.map.put(dm.getDataModelId(), dm);
		dao.map.put(dm1.getDataModelId(), dm1);
		System.out.println(dm.getContent());
		dao.save(dm);
		dao.delete(dm);
		System.out.println(dao.map);
		
		
	}

}
