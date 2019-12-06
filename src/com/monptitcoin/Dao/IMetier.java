package com.monptitcoin.Dao;

import java.util.List;

public interface IMetier <A>{
	
	public A save(A a);
	public List<A> getAll();
	public A update(A a);
	public void delete(Integer id);
	public A getById(Integer id);
	

}
