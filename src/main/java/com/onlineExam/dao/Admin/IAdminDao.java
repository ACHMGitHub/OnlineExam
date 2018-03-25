package com.onlineExam.dao.Admin;

import com.onlineExam.dao.BaseDao.IBaseDao;
import com.onlineExam.entity.Admin;
import java.util.List;

public interface IAdminDao extends IBaseDao<Admin> {

    //Add
    void save(String id, String pw);

    //Delete
    void delete(int uuid);
    void delete(String id);
    void deleteBySex(String sex);

    //Search
    Admin getByUuid(int uuid);
    Admin getById(String id);
    Admin getByCard(String card);
    Admin getByPhone(String phone);
    List<Admin> getBySex(String sex);
}
