package com.onlineExam.dao.TestPaper;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.TestPaper;

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

}
