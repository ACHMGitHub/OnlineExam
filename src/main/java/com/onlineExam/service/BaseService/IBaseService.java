package com.onlineExam.service.BaseService;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

    /**
     * 持久化
     *
     * @param entity
     *
     * @return oid
     */
    Serializable save(T entity);

    /**
     * 是否允许持久化
     * 判断各个字段是否为空值
     * @param entity 要持久的实体
     * @return true 为允许
     */
    boolean allowToSave(T entity);

    /**
     * 更新
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 保存或更新
     *
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 通过对象标识符获取对象
     *
     * @param oid
     * @return 标识符对应的对象，没找大则返回null
     */
    T findById(Serializable oid);

    /**
     * 返回所有对象的列表
     *
     * @return
     */
    List<T> findAll();

    /**
     * 向分页对象中设置记录
     *
     * @param detachedCriteria
     *            离线查询对象
     * @param startIndex
     *            开始索引
     * @param pageSize
     *            每页记录数
     * @return
     */
    List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize);

    /**
     * 查找满足条件的总记录数
     *
     * @param detachedCriteria
     * @return
     */
    Integer findRecordNumByPage(DetachedCriteria detachedCriteria);

}
