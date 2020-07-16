package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController<T>{
	
	public boolean	delete(DataModel<T>[] dataModels) {
		return false;
		
	} 
	
	public DataModel<T>[]	get(DataModel<T>[] dataModels){
		return dataModels;
		
	}
	
	public boolean	update(DataModel<T>[] dataModels) {
		return false;
		
	}
}
