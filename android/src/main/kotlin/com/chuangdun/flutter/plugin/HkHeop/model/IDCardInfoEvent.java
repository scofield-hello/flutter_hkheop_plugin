package com.chuangdun.flutter.plugin.HkHeop.model;

import com.google.gson.annotations.SerializedName;

public class IDCardInfoEvent{
    @SerializedName("IDCardInfo")
    private IDCardInfo cardInfo;
    @SerializedName("IDCardPicURL")
    private String cardPicURL;
    private Integer picturesNumber;

    public IDCardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(IDCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getCardPicURL() {
        return cardPicURL;
    }

    public void setCardPicURL(String cardPicURL) {
        this.cardPicURL = cardPicURL;
    }

    public Integer getPicturesNumber() {
        return picturesNumber;
    }

    public void setPicturesNumber(Integer picturesNumber) {
        this.picturesNumber = picturesNumber;
    }

    @Override
    public String toString() {
        return "IDCardInfoEvent{" +
                "iDCardInfo=" + cardInfo +
                ", iDCardPicURL='" + cardPicURL + '\'' +
                ", picturesNumber=" + picturesNumber +
                '}';
    }
}
