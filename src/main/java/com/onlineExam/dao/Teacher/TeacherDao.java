package com.onlineExam.dao.Teacher;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Teacher;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDao extends BaseDaoImpl<Teacher> implements ITeacherDao{

    @Override
    public void save(String id, String pw) {
        Teacher tch = new Teacher();
        tch.setId(id);
        tch.setPw(pw);
        tch.setName("");
        tch.setSex(1);
        tch.setPhone("");
        tch.setTitle("");
        tch.setCard("");
        save(tch);
    }

    @Override
    public void delete(int uuid) {
        Teacher tch = findById(uuid);
        if(tch == null)
            return;
        else
            delete(tch);
    }

    @Override
    public void delete(String id) {
        Teacher tch = getById(id);
        if(tch != null)
            delete(tch);
    }

    @Override
    public Teacher getByUuid(int uuid) {
        return findById(uuid);
    }

    @Override
    public Teacher getById(String id) {
        List tchs = getHibernateTemplate().find("from Teacher where id = ?", id);
        if(tchs.size() > 0)
            return (Teacher) tchs.get(0);
        else
            return null;
    }

    @Override
    public Teacher getByCard(String card) {
        List tchs = getHibernateTemplate().find("from Teacher where card = ?", card);
        if(tchs.size() > 0)
            return (Teacher) tchs.get(0);
        else
            return null;
    }

    @Override
    public Teacher getByPhone(String phone) {
        List tchs = getHibernateTemplate().find("from Teacher where phone = ?", phone);
        if(tchs.size() > 0)
            return (Teacher) tchs.get(0);
        else
            return null;
    }

    @Override
    public List<Teacher> getBySex(int sex) {
        List tchs = getHibernateTemplate().find("from Teacher where sex = ?", sex);
        if(tchs.size() > 0)
            return (List<Teacher>)tchs;
        else
            return null;
    }

    @Override
    public List<Teacher> getByName(String name) {
        List tchs = getHibernateTemplate().find("from Teacher where name like ?", name);
        if(tchs.size() > 0)
            return (List<Teacher>)tchs;
        else
            return null;
    }

    @Override
    public List<Teacher> getByTitle(String title) {
        List tchs = getHibernateTemplate().find("from Teacher where title like ?", title);
        if(tchs.size() > 0)
            return (List<Teacher>)tchs;
        else
            return null;
    }

    @Override
    public List<Teacher> findAllByPage(int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
        return this.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<Teacher> findTitleByPage(String title, int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class).add( Property.forName("title").eq(title));
        return this.findByPage(dc, startIndex, pageSize);
    }
}
