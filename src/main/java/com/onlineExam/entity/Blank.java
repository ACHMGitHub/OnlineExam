package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_blank", schema = "exam", catalog = "")
public class Blank {
    private Integer uuid;
    private String question;
    private String answer;
    private String analyse;
    private Teacher teacher;
    private Collection<TPBlank> testPapers;

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
    @Column(name = "bl_question", nullable = false, length = 255)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "bl_answer", nullable = false, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "bl_analyse", nullable = false, length = 255)
    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blank blank = (Blank) o;

        if (uuid != null ? !uuid.equals(blank.uuid) : blank.uuid != null) return false;
        if (question != null ? !question.equals(blank.question) : blank.question != null) return false;
        if (answer != null ? !answer.equals(blank.answer) : blank.answer != null) return false;
        if (analyse != null ? !analyse.equals(blank.analyse) : blank.analyse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (analyse != null ? analyse.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bl_tch_id", referencedColumnName = "uuid", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "blank", orphanRemoval = true)
    public Collection<TPBlank> getTestPapers() {
        return testPapers;
    }

    public void setTestPapers(Collection<TPBlank> testPapers) {
        this.testPapers = testPapers;
    }
}
