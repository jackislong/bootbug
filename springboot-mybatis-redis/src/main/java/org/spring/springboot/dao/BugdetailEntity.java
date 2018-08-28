package org.spring.springboot.dao;

import java.io.Serializable;

public class BugdetailEntity  implements Serializable {
    private Integer id;

    private String bugno;

    private String proname;

    private String demandname;

    private String state;

    private String dealname;

    private String grade;

    private String seriousstate;

    private String defectstate;

    private String comment;

    private String detector;

    private String checker;

    private String detectiontime;

    private String updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugno() {
        return bugno;
    }

    public void setBugno(String bugno) {
        this.bugno = bugno == null ? null : bugno.trim();
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public String getDemandname() {
        return demandname;
    }

    public void setDemandname(String demandname) {
        this.demandname = demandname == null ? null : demandname.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname == null ? null : dealname.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSeriousstate() {
        return seriousstate;
    }

    public void setSeriousstate(String seriousstate) {
        this.seriousstate = seriousstate == null ? null : seriousstate.trim();
    }

    public String getDefectstate() {
        return defectstate;
    }

    public void setDefectstate(String defectstate) {
        this.defectstate = defectstate == null ? null : defectstate.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getDetector() {
        return detector;
    }

    public void setDetector(String detector) {
        this.detector = detector == null ? null : detector.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getDetectiontime() {
        return detectiontime;
    }

    public void setDetectiontime(String detectiontime) {
        this.detectiontime = detectiontime == null ? null : detectiontime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}