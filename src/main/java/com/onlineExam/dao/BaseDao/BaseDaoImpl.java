package com.onlineExam.dao.BaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    // 存储泛型的实际参数
    private Class<T> clazz;

    public BaseDaoImpl() {
        // 谁实现该类，这就是谁的类字节码
        Class c = this.getClass();
        // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        Type type = c.getGenericSuperclass();
        // 将类型强转为参数化类型
        ParameterizedType pType = (ParameterizedType) type;
        // 获取该类的父类的所有实际类型参数，也就是泛型的实际参数
        // 这里也就是获取BaseDaoImpl的实际类型参数
        Type[] actualTypeArguments = pType.getActualTypeArguments();
        // 将实际类型参数赋值给成员变量
        clazz = (Class<T>) (actualTypeArguments[0]);
    }

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Serializable save(T entity) {
        return this.getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public T findById(Serializable oid) {
        return (T) this.getHibernateTemplate().get(this.clazz, oid);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from " + this.clazz.getSimpleName());
    }

    @Override
    public Integer findRecordNum(DetachedCriteria detachedCriteria) {
        // 设置记录数投影
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        // 将投影置为空
        detachedCriteria.setProjection(null);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize) {
        // 指定hibernate在连接查询时，只封装成一个对象
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, startIndex, pageSize);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        // 获取当前session
        Session session = this.getSessionFactory().getCurrentSession();
        // 获取命名查询对象
        Query query = session.getNamedQuery(queryName);
        int i = 0;
        // 设置参数
        if (objects != null) {
            for (Object object : objects) {
                query.setParameter(i++, object);
            }
        }
        query.executeUpdate();
    }

    /* *****************************自己加的*******************************************************************/
    @Override
    public DetachedCriteria getDetachedCriteria() {
        return DetachedCriteria.forClass(clazz);
    }
}
