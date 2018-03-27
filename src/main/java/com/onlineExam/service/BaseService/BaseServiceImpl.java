package com.onlineExam.service.BaseService;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements IBaseService<T>{

//    @Autowired
    IBaseService<T> baseDaoImpl;

    @Override
    public Serializable save(T entity) {
        baseDaoImpl.save(entity);
        return null;
    }

    @Override
    public void update(T entity) {
        baseDaoImpl.update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        baseDaoImpl.saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        baseDaoImpl.delete(entity);
    }

    @Override
    public T findById(Serializable oid) {
        return baseDaoImpl.findById(oid);
    }

    @Override
    public List<T> findAll() {
        return baseDaoImpl.findAll();
    }

    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        return baseDaoImpl.findByPage(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public Integer findRecordNumByPage(DetachedCriteria detachedCriteria) {
        return baseDaoImpl.findRecordNumByPage(detachedCriteria);
    }
}
