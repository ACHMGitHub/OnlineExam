package com.onlineExam.dao.BaseDao;

import java.util.List;

public interface BaseDao<T> {

	public void save(T t);

	public int delete(T t);

	public void update(T t);
	
	public void saveOrUpdate(T t);
	
	public List<T> retrieve(String hql, Object... objects);

	public List<T> retrieveAll();

}
