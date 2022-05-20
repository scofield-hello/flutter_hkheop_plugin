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

    