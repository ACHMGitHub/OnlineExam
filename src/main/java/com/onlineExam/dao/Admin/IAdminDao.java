package com.onlineExam.dao.Admin;

import com.onlineExam.entity.Admin;

import java.util.List;

public interface IAdminDao {

    //Add
    int save(Admin admin);
    int save(String id, String pw);

    //Delete
    int delete(Admin admin);
    int delete(int uuid);
    int delete(String id);
    int deleteBySex(String sex);

    //Search
    Admin getByUuid(int uuid);
    Admin getById(String id);
    Admin getByCard(String card);
    Admin getByPhone(String phone);
    List<Admin> getBySex(String sex);

    //Alter
    void alterCard(Admin admin, String newCard);
    void alterPhone(Admin admin, String newPhone);
    void alterSex(Admin admin, String newSex);
    void alterName(Admin admin, String newName);
    void alterPW(Admin admin, String newPW);
    void alterId(Admin admin, String newId);
}
