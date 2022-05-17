package com.chuangdun.flutter.plugin.HkHeop.model;

import com.google.gson.annotations.SerializedName;

public class IDCardInfo{
    private String name;
    private String sex;
    private String birth;
    private String addr;
    @SerializedName("IDCardNo")
    private String cardNo;
    private String issuingAuthority;
    private String startDate;
    private String endDate;
    private Integer nation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String iDCardNo) {
        this.cardNo = iDCardNo;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "IDCardInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", addr='" + addr + '\'' +
                ", iDCardNo='" + cardNo + '\'' +
                ", issuingAuthority='" + issuingAuthority + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", nation=" + nation +
                '}';
    }
}
