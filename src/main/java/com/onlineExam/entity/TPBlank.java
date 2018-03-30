package com.onlineExam.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_tpblank", schema = "exam", catalog = "")
public class TPBlank {
    private Integer uuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPBlank tpBlank = (TPBlank) o;

        if (uuid != null ? !uuid.equals(tpBlank.uuid) : tpBlank.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
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
