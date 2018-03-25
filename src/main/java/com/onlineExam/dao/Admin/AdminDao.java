package com.onlineExam.dao.Admin;

import com.onlineExam.entity.Admin;

import java.util.List;

public class AdminDao implements IAdminDao{
    @Override
    public int save(Admin admin) {
        return 0;
    }

    @Override
    public int save(String id, String pw) {
        return 0;
    }

    @Override
    public int delete(Admin admin) {
        return 0;
    }

    @Override
    public int delete(int uuid) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int deleteBySex(String sex) {
        return 0;
    }

    @Override
    public Admin getByUuid(int uuid) {
        return null;
    }

    @Override
    public Admin getById(String id) {
        return null;
    }

    @Override
    public Admin getByCard(String card) {
        return null;
    }

    @Override
    public Admin getByPhone(String phone) {
        return null;
    }

    @Override
    public List<Admin> getBySex(String sex) {
        return null;
    }

    @Override
    public void alterCard(Admin admin, String newCard) {

    }

    @Override
    public void alterPhone(Admin admin, String newPhone) {

    }

    @Override
    public void alterSex(Admin admin, String newSex) {

    }

    @Override
    public void alterName(Admin admin, String newName) {

    }

    @Override
    public void alterPW(Admin admin, String newPW) {

    }

    @Override
    public void alterId(Admin admin, String newId) {

    }
}
