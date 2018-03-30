package com.onlineExam.service.Blank;

import com.onlineExam.entity.Blank;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface IBlankService extends IBaseService<Blank>{

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
}
