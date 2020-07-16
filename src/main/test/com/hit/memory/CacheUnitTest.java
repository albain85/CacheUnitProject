/*
  package com.hit.memory;
 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dm.DataModel;

public class CacheUnitTest {
	
	@Test
	public void test(){
		int size=3;
		LRUAlgoCacheImpl n = new LRUAlgoCacheImpl(size);
		DataModel<Integer> i1 = new DataModel<Integer>((long) 1,311);
		DataModel<Integer> i2 = new DataModel<Integer>((long) 2,222);
		DataModel<Integer> i3 = new DataModel<Integer>((long) 3,333);
		DataModel<Integer> i4 = new DataModel<Integer>((long) 4,444);

		DataModel[] data= {i1,i2,i3};
		DataModel[] data1= {i1,i2,i3,i4};

		CacheUnit c = new CacheUnit<Object>(n);
		assertNull(c.putDataModels(data));
		assertNotNull(c.putDataModels(data1));
		assertEquals(size,data.length);
	}

}

*/