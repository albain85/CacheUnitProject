package com.hit.dm;

import java.io.Serializable;

public class DataModel<T> extends Object implements Serializable{
	
//	private static final long serialVersionUID = 1L;
	
	private long id;
	private T cont;
	
	public DataModel(Long id, T content){
		this.id = id;
		this.cont =content;
	};
	
	public boolean	equals(Object obj) {
		return false;
		}
	
	public T getContent() {
		return cont;
		}

	public Long	getDataModelId() {
		return id;
		}
	
	public int	hashCode() {
		return 0;
		}
	public void	setContent(T content) {
		this.cont = content;
	}
	public void	setDataModelId(java.lang.Long id) {
		this.id = id;
	}
	public String	toString() {
		return null;
		}
}
