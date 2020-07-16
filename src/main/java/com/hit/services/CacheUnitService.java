package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitService<T> {

	boolean	delete(DataModel<T>[] dataModels) {
		return false;
		
	} 
	
	DataModel<T>[]	get(DataModel<T>[] dataModels){
		return dataModels;
		
	}
	
	boolean	update(DataModel<T>[] dataModels) {
		return false;
		
	}
}

/*
package com.hit.services;

import java.util.ArrayList;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.RandomAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T>
extends java.lang.Object {
	private CacheUnit cacheUnit;
	private DaoFileImpl<T> daoFileImpl;
	private static int id=0;
	
	public CacheUnitService() throws Exception {
		this.cacheUnit = new CacheUnit(new LRUAlgoCacheImpl<Long, String>(3));
		this.daoFileImpl = new DaoFileImpl("src/main/resources/datasource"+ (id++) +".txt");
	}
	
	public boolean update(DataModel<T>[] dataModels) {
		// remove from DAO if exists	
		for (DataModel<T> dataModel : dataModels) {
			this.daoFileImpl.delete(dataModel);
		}
		
		DataModel<T>[] replaced = this.cacheUnit.putDataModels(dataModels);
		
		if (replaced.length > 0) {
			for (DataModel<T> dataModel : replaced) {
				this.daoFileImpl.save(dataModel);
			}
		}
		
		return true;
	}
	
	public boolean delete(DataModel<T>[] dataModels) {	
		Long[] ids = new Long[dataModels.length];
		
		for (int i=0; i < dataModels.length; i++) {
			this.daoFileImpl.delete(dataModels[i]);
			ids[i] = dataModels[i].getDataModelId();
		}
		
		this.cacheUnit.removeDataModels(ids);
		return true;
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels) {
		DataModel<T>[] result;
		Long[] ids = new Long[dataModels.length];
		
		for (int i=0; i < dataModels.length; i++) {
			ids[i] = dataModels[i].getDataModelId();
		}
		
		result = this.cacheUnit.getDataModels(ids);
		
		if (result.length < dataModels.length) // if not all DataModels has been found in CacheUnit, continue checking DAO
		{
			ArrayList<DataModel<T>> fromDaoFileImpl = new ArrayList<DataModel<T>>();
			
			for (Long id : ids) {
				DataModel<T> dataModel = this.daoFileImpl.find(id);
				if (dataModel != null) {
					fromDaoFileImpl.add(dataModel);
				}
			}
			
			DataModel<T>[] stockArr = new DataModel[fromDaoFileImpl.size()];
			stockArr = fromDaoFileImpl.toArray(stockArr);
			
			result = combine(result, stockArr);
		}
		
		return result;
	}
	
	private DataModel<T>[] combine(DataModel<T>[] a, DataModel<T>[] b){
        int length = a.length + b.length;
        DataModel<T>[] result = new DataModel[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}

*/