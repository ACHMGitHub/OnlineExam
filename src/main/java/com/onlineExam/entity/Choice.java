package com.onlineExam.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tb_choice", schema = "exam", catalog = "")
public class Choice {
    private Integer uuid;
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String answer;
    private String analyse;
    private Teacher teacher;
    private Collection<TPChoice> testPapers;

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
    @Column(name = "ch_question", nullable = false, length = 255)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "ch_a", nullable = false, length = 255)
    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    @Basic
    @Column(name = "ch_b", nullable = false, length = 255)
    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    @Basic
    @Column(name = "ch_c", nullable = false, length = 255)
    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    @Basic
    @Column(name = "ch_d", nullable = false, length = 255)
    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    @Basic
    @Column(name = "ch_answer", nullable = false, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "ch_analyse", nullable = false, length = 255)
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

        Choice choice = (Choice) o;

        if (uuid != null ? !uuid.equals(choice.uuid) : choice.uuid != null) return false;
        if (question != null ? !question.equals(choice.question) : choice.question != null) return false;
        if (choiceA != null ? !choiceA.equals(choice.choiceA) : choice.choiceA != null) return false;
        if (choiceB != null ? !choiceB.equals(choice.choiceB) : choice.choiceB != null) return false;
        if (choiceC != null ? !choiceC.equals(choice.choiceC) : choice.choiceC != null) return false;
        if (choiceD != null ? !choiceD.equals(choice.choiceD) : choice.choiceD != null) return false;
        if (answer != null ? !answer.equals(choice.answer) : choice.answer != null) return false;
        if (analyse != null ? !analyse.equals(choice.analyse) : choice.analyse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (choiceA != null ? choiceA.hashCode() : 0);
        result = 31 * result + (choiceB != null ? choiceB.hashCode() : 0);
        result = 31 * result + (choiceC != null ? choiceC.hashCode() : 0);
        result = 31 * result + (choiceD != null ? choiceD.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (analyse != null ? analyse.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ch_tch_id", referencedColumnName = "uuid", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "choice")
    public Collection<TPChoice> getTestPapers() {
        return testPapers;
    }

    public void setTestPapers(Collection<TPChoice> testPapers) {
        this.testPapers = testPapers;
    }
}
