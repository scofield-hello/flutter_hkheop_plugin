package com.chuangdun.flutter.plugin.HkHeop.model;

import com.google.gson.annotations.SerializedName;

public class AcsCfgDataX {
    private AcsCfgX AcsCfg;

    public AcsCfgX getAcsCfg() {
        return AcsCfg;
    }

    public void setAcsCfg(AcsCfgX acsCfg) {
        AcsCfg = acsCfg;
    }

    @Override
    public String toString() {
        return "AcsCfgDataX{" +
                "AcsCfg=" + AcsCfg +
                '}';
    }
}

class AcsCfgX{
    private Boolean thermalEnabled;
    private String distanceUnit;
    private Float distance;
    private Integer highestThermalThreshold;
    private Integer lowestThermalThreshold;
    @SerializedName("QRCodeEnabled")
    private Boolean qrCodeEnabled;
    private Integer authType;


    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }



    public Boolean getThermalEnabled() {
        return thermalEnabled;
    }

    public void setThermalEnabled(Boolean thermalEnabled) {
        this.thermalEnabled = thermalEnabled;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getHighestThermalThreshold() {
        return highestThermalThreshold;
    }

    public void setHighestThermalThreshold(Integer highestThermalThreshold) {
        this.highestThermalThreshold = highestThermalThreshold;
    }

    public Integer getLowestThermalThreshold() {
        return lowestThermalThreshold;
    }

    public void setLowestThermalThreshold(Integer lowestThermalThreshold) {
        this.lowestThermalThreshold = lowestThermalThreshold;
    }

    public Boolean getQrCodeEnabled() {
        return qrCodeEnabled;
    }

    public void setQrCodeEnabled(Boolean qrCodeEnabled) {
        this.qrCodeEnabled = qrCodeEnabled;
    }


    @Override
    public String toString() {
        return "AcsCfgX{" +
                "thermalEnabled=" + thermalEnabled +
                ", distanceUnit='" + distanceUnit + '\'' +
                ", distance=" + distance +
                ", highestThermalThreshold=" + highestThermalThreshold +
                ", lowestThermalThreshold=" + lowestThermalThreshold +
                ", qrCodeEnabled=" + qrCodeEnabled +
                ", authType=" + authType +
                '}';
    }
}
