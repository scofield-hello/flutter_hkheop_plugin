package com.chuangdun.flutter.plugin.HkHeop.model;

public class CardEvent{
    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "CardEvent{" +
                "cardNo='" + cardNo + '\'' +
                '}';
    }
}
