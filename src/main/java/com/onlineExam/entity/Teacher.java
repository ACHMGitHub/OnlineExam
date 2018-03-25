package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_teacher", schema = "exam", catalog = "")
public class Teacher {
    private Integer uuid;
    private String id;
    private String pw;
    private String name;
    private Integer sex;
    private String phone;
    private String card;
    private String title;
    private Collection<Blank> blanks;
    private Collection<Choice> choices;

    @Id
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "tch_id", nullable = false, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tch_pw", nullable = false, length = 45)
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Basic
    @Column(name = "tch_name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tch_sex", nullable = false)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "tch_phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "tch_card", nullable = false, length = 45)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Basic
    @Column(name = "tch_title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (uuid != null ? !uuid.equals(teacher.uuid) : teacher.uuid != null) return false;
        if (id != null ? !id.equals(teacher.id) : teacher.id != null) return false;
        if (pw != null ? !pw.equals(teacher.pw) : teacher.pw != null) return false;
        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        if (sex != null ? !sex.equals(teacher.sex) : teacher.sex != null) return false;
        if (phone != null ? !phone.equals(teacher.phone) : teacher.phone != null) return false;
        if (card != null ? !card.equals(teacher.card) : teacher.card != null) return false;
        if (title != null ? !title.equals(teacher.title) : teacher.title != null) return false;

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
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "teacher")
    public Collection<Blank> getBlanks() {
        return blanks;
    }

    public void setBlanks(Collection<Blank> blanks) {
        this.blanks = blanks;
    }

    @OneToMany(mappedBy = "teacher")
    public Collection<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Collection<Choice> choices) {
        this.choices = choices;
    }
}
