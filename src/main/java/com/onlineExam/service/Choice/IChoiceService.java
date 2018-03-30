package com.onlineExam.service.Choice;

import com.onlineExam.entity.Choice;
import com.onlineExam.service.IBaseService;

import java.util.List;

public interface IChoiceService extends IBaseService<Choice>{

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
    Choice getByUuid(int uuid);
}
