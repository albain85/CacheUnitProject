/*package com.hit.memory;

import com.hit.dm.DataModel;
import com.hit.algorithm.IAlgoCache;

public class CacheUnit<T> extends Object {

	IAlgoCache<Long,DataModel<T>> algo;
	
	CacheUnit(IAlgoCache<Long,DataModel<T>> algo){
		this.algo = algo;
	};
	
	@SuppressWarnings("unchecked")	//get the element from LRU,NRU,Random algo's and put it in datamodel array and return it
	public DataModel<T>[] getDataModels(Long[] ids) {	
		int count = 0;
		DataModel<T>[] datamodel = new DataModel[ids.length];
		for(Long i : ids) {
			if(algo.getElement(i) != null) {
				datamodel[count] = algo.getElement(i);
				count++;
			}	
		}
			
		return datamodel;
	}
	
	@SuppressWarnings("unchecked")	//put data using LRU,NRU,Random algo's and the return element put into dm array and return it in the end
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) {
		DataModel<T>[] dm = new DataModel[datamodels.length];
		for(int i = 0 ; i < datamodels.length ; i++) {
			dm[i] = algo.putElement(datamodels[i].getDataModelId(), (DataModel<T>) datamodels[i].getContent());
		
		}
		
		return dm;
	}
	
	public void removeDataModels(Long[] ids) {
		for(int i = 0 ; i < ids.length ; i++) {
		algo.removeElement(ids[i]);
		}
	}
	
}

*/
	

package com.hit.memory;

import com.hit.algorithm.IAlgoCache; 
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

public class CacheUnit<T> extends java.lang.Object {
	com.hit.algorithm.IAlgoCache<Long,DataModel<T>> algo1;

	
	public CacheUnit(com.hit.algorithm.IAlgoCache<Long,DataModel<T>> algo) {
		this.algo1=algo;
		
	}
	
	public DataModel<T>[] getDataModels(Long[] ids) {
		int count = 0;
		DataModel<T>[] datamodel = new DataModel[ids.length];
		for(long i:ids) {
			if (algo1.getElement(i) != null) {
				datamodel[count]=algo1.getElement(i);
				count++;
			}
	
				 count++;
				}
			
			return datamodel;
	
		}
		
	
	@SuppressWarnings("unchecked")
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels){
		DataModel<T>[] datamodel_return = new DataModel[datamodels.length];
        int index=0;
		for(int i=0;i<datamodels.length;i++) {
		
			DataModel<T> check= algo1.putElement(datamodels[i].getDataModelId(),  datamodels[i]);
		if ( check!= null) 
			datamodel_return[index++]=check;
		
		}
          if (index==0)
        	  return null;
		return datamodel_return;}
	
	
	public void removeDataModels(Long[] ids) {
		
	int count =0;
		for(long i:ids) {
			if (algo1.getElement(i) != null) 
				algo1.removeElement(i);
				count++;
			}
		
		}
		
		
		
}	
	
	
	

