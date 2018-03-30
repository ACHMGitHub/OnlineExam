package com.onlineExam.dao.Student;

import com.onlineExam.dao.BaseDao.BaseDaoImpl;
import com.onlineExam.entity.Admin;
import com.onlineExam.entity.Student;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao extends BaseDaoImpl<Student> implements IStudentDao {

    @Override
    public void save(String id, String pw) {
        Student stu = new Student();
        stu.setId(id);
        stu.setPw(pw);
        stu.setName("");
        stu.setCard("");
        stu.setPhone("");
        stu.setSex(1);
        stu.setClassName("");
    }

    @Override
    public void delete(int uuid) {
        Student stu = findById(uuid);
        if(stu != null)
            delete(stu);
    }

    @Override
    public void delete(String id) {
        Student stu = getById(id);
        if(stu != null)
            delete(stu);
    }

    @Override
    public void deleteBySex(int sex) {

    }

    @Override
    public Student getByUuid(int uuid) {
        return findById(uuid);
    }

    @Override
    public Student getById(String id) {
        List students = getHibernateTemplate().find("from Student where id = ?", id);
        if(students.size() > 0)
            return (Student)students.get(0);
        else
            return null;
    }

    @Override
    public Student getByCard(String card) {
        return (Student)getHibernateTemplate().find("from Student where card = ?", card).get(0);
    }

    @Override
    public Student getByPhone(String phone) {
        return (Student)getHibernateTemplate().find("from Student where phone = ?", phone).get(0);
    }

    @Override
    public List<Student> getBySex(int sex) {
        return (List<Student>)getHibernateTemplate().find("from Student where sex = ?", sex);
    }

    @Override
    public List<Student> getByName(String name) {
        return (List<Student>)getHibernateTemplate().find("from Student where name like ?", name);
    }

    @Override
    public List<Student> findAllByPage(int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Student.class);
        return this.findByPage(dc, startIndex, pageSize);
    }

    @Override
    public List<Student> findClassByPage(String className, int startIndex, int pageSize) {
        DetachedCriteria dc = DetachedCriteria.forClass(Student.class).add(Property.forName("className").eq(className));
        return this.findByPage(dc, startIndex, pageSize);
    }

}
