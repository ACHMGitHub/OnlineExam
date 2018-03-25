package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tpblank", schema = "exam", catalog = "")
public class TPBlank {
    private Integer uuid;
    private Integer testPaperId;
    private Integer blankId;
    private TestPaper testPaper;
    private Blank blank;

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
    @Column(name = "tpb_tp_id", nullable = false)
    public Integer getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Integer testPaperId) {
        this.testPaperId = testPaperId;
    }

    @Basic
    @Column(name = "tpb_bl_id", nullable = false)
    public Integer getBlankId() {
        return blankId;
    }

    public void setBlankId(Integer blankId) {
        this.blankId = blankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPBlank tpBlank = (TPBlank) o;

        if (uuid != null ? !uuid.equals(tpBlank.uuid) : tpBlank.uuid != null) return false;
        if (testPaperId != null ? !testPaperId.equals(tpBlank.testPaperId) : tpBlank.testPaperId != null) return false;
        if (blankId != null ? !blankId.equals(tpBlank.blankId) : tpBlank.blankId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (testPaperId != null ? testPaperId.hashCode() : 0);
        result = 31 * result + (blankId != null ? blankId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tpb_tp_id", referencedColumnName = "uuid", nullable = false)
    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

    @ManyToOne
    @JoinColumn(name = "tpb_bl_id", referencedColumnName = "uuid", nullable = false)
    public Blank getBlank() {
        return blank;
    }

    public void setBlank(Blank blank) {
        this.blank = blank;
    }
}
