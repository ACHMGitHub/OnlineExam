package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_testpaper", schema = "exam", catalog = "")
public class TestPaper {
    private Integer uuid;
    private Integer timeLimit;
    private Integer totalGrade;
    private Integer choiceNum;
    private Integer blankNum;
    private Collection<StudentTP> stuTestPapers;
    private Course course;
    private Collection<TPBlank> blanks;
    private Collection<TPChoice> choices;

    @Id
    @Column(name = "uuid", nullable = false)
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "tp_time", nullable = false)
    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Basic
    @Column(name = "tp_tg", nullable = false)
    public Integer getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Integer totalGrade) {
        this.totalGrade = totalGrade;
    }

    @Basic
    @Column(name = "tb_choice", nullable = false)
    public Integer getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(Integer choiceNum) {
        this.choiceNum = choiceNum;
    }

    @Basic
    @Column(name = "tb_blank", nullable = false)
    public Integer getBlankNum() {
        return blankNum;
    }

    public void setBlankNum(Integer blankNum) {
        this.blankNum = blankNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPaper testPaper = (TestPaper) o;

        if (uuid != null ? !uuid.equals(testPaper.uuid) : testPaper.uuid != null) return false;
        if (timeLimit != null ? !timeLimit.equals(testPaper.timeLimit) : testPaper.timeLimit != null) return false;
        if (totalGrade != null ? !totalGrade.equals(testPaper.totalGrade) : testPaper.totalGrade != null) return false;
        if (choiceNum != null ? !choiceNum.equals(testPaper.choiceNum) : testPaper.choiceNum != null) return false;
        if (blankNum != null ? !blankNum.equals(testPaper.blankNum) : testPaper.blankNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (timeLimit != null ? timeLimit.hashCode() : 0);
        result = 31 * result + (totalGrade != null ? totalGrade.hashCode() : 0);
        result = 31 * result + (choiceNum != null ? choiceNum.hashCode() : 0);
        result = 31 * result + (blankNum != null ? blankNum.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "testPaper")
    public Collection<StudentTP> getStuTestPapers() {
        return stuTestPapers;
    }

    public void setStuTestPapers(Collection<StudentTP> stuTestPapers) {
        this.stuTestPapers = stuTestPapers;
    }

    @ManyToOne
    @JoinColumn(name = "tp_coz_id", referencedColumnName = "uuid", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(mappedBy = "testPaper")
    public Collection<TPBlank> getBlanks() {
        return blanks;
    }

    public void setBlanks(Collection<TPBlank> blanks) {
        this.blanks = blanks;
    }

    @OneToMany(mappedBy = "testPaper")
    public Collection<TPChoice> getChoices() {
        return choices;
    }

    public void setChoices(Collection<TPChoice> choices) {
        this.choices = choices;
    }
}
