package com.onlineExam.dao.TestPaper;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.TestPaper;
import org.hibernate.criterion.DetachedCriteria;

public interface ITestPaperDao extends IBaseDao<TestPaper>{

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
    TestPaper getByUuid(int uuid);

    /**
     * 按课程号寻找试卷
     * 为detachedCriteria添加与Course的关联名为course
     * @param detachedCriteria 原有查询条件
     * @param id 课程号
     * @return 加上课程号查询的查询条件
     */
    DetachedCriteria findByCourse(DetachedCriteria detachedCriteria, Integer id);
}
