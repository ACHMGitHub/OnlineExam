package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_admin", schema = "exam", catalog = "")
public class Admin {
    private Integer uuid;
    private String id;
    private String pw;
    private String name;
    private Integer sex;
    private String phone;
    private String card;

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
    @Column(name = "ad_id", nullable = false, length = 45)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ad_pw", nullable = false, length = 45)
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Basic
    @Column(name = "ad_name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ad_sex", nullable = false, length = 45)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "ad_phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "ad_card", nullable = false, length = 45)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (uuid != null ? !uuid.equals(admin.uuid) : admin.uuid != null) return false;
        if (id != null ? !id.equals(admin.id) : admin.id != null) return false;
        if (pw != null ? !pw.equals(admin.pw) : admin.pw != null) return false;
        if (name != null ? !name.equals(admin.name) : admin.name != null) return false;
        if (sex != null ? !sex.equals(admin.sex) : admin.sex != null) return false;
        if (phone != null ? !phone.equals(admin.phone) : admin.phone != null) return false;
        if (card != null ? !card.equals(admin.card) : admin.card != null) return false;

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
        return result;
    }
}
