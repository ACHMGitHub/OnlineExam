package com.onlineExam.service.TestPaper;

import com.onlineExam.entity.TestPaper;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface ITestPaperService extends IBaseService<TestPaper>{

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
