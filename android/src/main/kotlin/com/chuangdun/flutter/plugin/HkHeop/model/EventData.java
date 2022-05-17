package com.chuangdun.flutter.plugin.HkHeop.model;

import com.google.gson.annotations.SerializedName;


public class EventData{
    @SerializedName("CardEvent")
    private CardEvent cardEvent;
    @SerializedName("QRCodeEvent")
    private QRCodeEvent qrCodeEvent;
    @SerializedName("IDCardInfoEvent")
    private IDCardInfoEvent cardInfoEvent;
    private int activePostCount;
    private int channelID;
    private String dateTime;
    private String eventDescription;
    private String eventState;
    private String eventType;
    private String ipAddress;
    private String ipv6Address;
    private String macAddress;
    private int portNo;
    private String protocol;

    public CardEvent getCardEvent() {
        return cardEvent;
    }

    public void setCardEvent(CardEvent cardEvent) {
        this.cardEvent = cardEvent;
    }

    public QRCodeEvent getQrCodeEvent() {
        return qrCodeEvent;
    }

    public void setQrCodeEvent(QRCodeEvent qrCodeEvent) {
        this.qrCodeEvent = qrCodeEvent;
    }

    public IDCardInfoEvent getCardInfoEvent() {
        return cardInfoEvent;
    }

    public void setCardInfoEvent(IDCardInfoEvent cardInfoEvent) {
        this.cardInfoEvent = cardInfoEvent;
    }

    public int getActivePostCount() {
        return activePostCount;
    }

    public void setActivePostCount(int activePostCount) {
        this.activePostCount = activePostCount;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getPortNo() {
        return portNo;
    }

    public void setPortNo(int portNo) {
        this.portNo = portNo;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "cardEvent=" + cardEvent +
                ", qrCodeEvent=" + qrCodeEvent +
                ", iDCardInfoEvent=" + cardInfoEvent +
                ", activePostCount=" + activePostCount +
                ", channelID=" + channelID +
                ", dateTime='" + dateTime + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventState='" + eventState + '\'' +
                ", eventType='" + eventType + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", ipv6Address='" + ipv6Address + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", portNo=" + portNo +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}

