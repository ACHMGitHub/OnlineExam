package com.onlineExam.dao.TestPaper;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.TestPaper;
import org.springframework.stereotype.Repository;

@Repository
public class TestPaperDao extends BaseDaoImpl<TestPaper> implements ITestPaperDao{

    @Override
    public void delete(int uuid) {
        TestPaper testPaper = getByUuid(uuid);
        if( testPaper == null)
            return;
        delete(testPaper);
    }

    @Override
    public TestPaper getByUuid(int uuid) {
        return findById(uuid);
    }
}
