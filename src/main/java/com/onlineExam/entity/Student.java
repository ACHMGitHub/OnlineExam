package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_student", schema = "exam", catalog = "")
public class Student {
    private Integer uuid;
    private String id;
    private String pw;
    private String name;
    private Integer sex;
    private String phone;
    private String card;
    private String stuClass;
    private Collection<StudentTP> stuTestPapers;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "stu_id", nullable = false, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "stu_pw", nullable = false, length = 45)
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Basic
    @Column(name = "stu_name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "stu_sex", nullable = false)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "stu_phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "stu_card", nullable = false, length = 45)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Basic
    @Column(name = "stu_class", nullable = false, length = 45)
    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (uuid != null ? !uuid.equals(student.uuid) : student.uuid != null) return false;
        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (pw != null ? !pw.equals(student.pw) : student.pw != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (sex != null ? !sex.equals(student.sex) : student.sex != null) return false;
        if (phone != null ? !phone.equals(student.phone) : student.phone != null) return false;
        if (card != null ? !card.equals(student.card) : student.card != null) return false;
        if (stuClass != null ? !stuClass.equals(student.stuClass) : student.stuClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (pw != null ? pw.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (stuClass != null ? stuClass.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "student")
    public Collection<StudentTP> getStuTestPapers() {
        return stuTestPapers;
    }

    public void setStuTestPapers(Collection<StudentTP> stuTestPapers) {
        this.stuTestPapers = stuTestPapers;
    }
}
