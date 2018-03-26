package com.onlineExam.service.BaseService;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements IBaseService<T>{

    @Autowired
    BaseDaoImpl<T> baseDaoImpl;

    @Override
    public Serializable save(T entity) {
        baseDaoImpl.save(entity);
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void saveOrUpdate(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public T findById(Serializable oid) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return null;
    }
}
