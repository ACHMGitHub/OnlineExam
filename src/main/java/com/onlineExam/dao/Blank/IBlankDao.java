package com.onlineExam.dao.Blank;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.Blank;

import java.util.List;

public interface IBlankDao extends IBaseDao<Blank>{

    /**
     * 主键删除
     * @param uuid
     */
    void delete(int uuid);

    /**
     * 按uuid查找
     * @param uuid
     * @return 唯一主码的题目
     */
    Blank getByUuid(int uuid);

    /**
     * 分页查询所有题目
     * @param startIndex 起始索引
     * @param pageSize 页面大小
     * @return 从startIndex到startIndex+pageSize的list集合
     */
    List<Blank> findAllByPage(int startIndex, int pageSize);
}
