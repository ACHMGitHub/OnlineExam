package com.onlineExam.dao.BaseDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<T> clazzType;
	
	public BaseDaoImpl() {
		Class<T> clazz = (Class<T>)this.getClass();
		ParameterizedType ptype = (ParameterizedType)clazz.getGenericSuperclass();
		Type[] types = ptype.getActualTypeArguments();
		this.clazzType = (Class<T>)types[0];
	}

	public Session getSession(){
		return this.getSessionFactory().getCurrentSession(); 
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public int delete(T t) {
		/*Session session=this.getSessionFactory().openSession();
		session.delete(t);
		session.beginTransaction().commit();
		session.close();*/
		this.getHibernateTemplate().delete(t);

		return 1;
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}
	
	@Override
	public List<T> retrieve(String hql, Object... objects){
		Query query = this.getSession().createQuery(hql);
		if(objects != null && objects.length > 0)
			for(int i=0;i<objects.length;i++)
				query.setParameter(i, objects[i]);
		
		return query.list();
	}
	
	@Override
	public List<T> retrieveAll() {
		return (List<T>)this.getHibernateTemplate().find("from "+clazzType.getSimpleName());
	}
}
