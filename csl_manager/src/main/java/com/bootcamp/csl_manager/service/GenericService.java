package com.bootcamp.csl_manager.service;

import com.bootcamp.csl_manager.utilities.ListResponse;
import com.bootcamp.csl_manager.utilities.SingleResponse;

public interface GenericService <T> {
	
	public ListResponse findAll();
	public SingleResponse findById(long id);
	public SingleResponse create(T t);
	public SingleResponse update(long id, T t);
	public SingleResponse deleteById(long id);

}
