package com.onlineExam.dao.BaseDao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

	/**
	 * 保存
	 * @param entity 实体
	 * @return oid
	 */
	Serializable save(T entity);

	/**
	 * 更新
	 * @param entity 实体
	 */
	void update(T entity);

	/**
	 * 保存或更新
	 * @param entity 实体
	 */
	void saveOrUpdate(T entity);

	/**
	 * 删除
	 * @param entity 实体
	 */
	void delete(T entity);

	/**
	 * 通过对象标识符获取对象
	 * @param oid 主码
	 * @return 标识符对应的对象，没找到则返回null
	 */
	T findById(Serializable oid);

	/**
	 * 返回所有对象的列表
	 * @return 集合
	 */
	List<T> findAll();

	/**
	 * 查找满足条件的总记录数
	 * @param detachedCriteria 查询条件
	 * @return 记录数
	 */
	Integer findRecordNum(DetachedCriteria detachedCriteria);

	/**
	 * 向分页对象中设置记录
	 * @param detachedCriteria 离线查询对象
	 * @param startIndex 开始索引
	 * @param pageSize 每页记录数
	 * @return 集合
	 */
	List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize);

	/**
	 * 通过条件查询
	 * @param detachedCriteria 查询条件
	 * @return 集合
	 */
	List<T> findByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 通用更新方法
	 * @param queryName 命名查询的名字，在映射文件中定义
	 * @param objects 参数列表
	 */
	void executeUpdate(String queryName, Object... objects);

	/* *****************************自己加的*******************************************************************/

	/**
	 * 得到实体类的查询条件
	 * @return 查询条件
	 */
	DetachedCriteria getDetachedCriteria();
}
