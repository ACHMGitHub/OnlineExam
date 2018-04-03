package com.onlineExam.service.TestPaper;

import com.onlineExam.entity.TestPaper;
import com.onlineExam.service.IBaseService;
import org.aspectj.weaver.ast.Test;

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

    /**
     * 按课程查询
     * @param courseId 课程主码
     * @return 该课程的所有试卷
     */
    List<TestPaper> findByCourse(Integer courseId);
}
