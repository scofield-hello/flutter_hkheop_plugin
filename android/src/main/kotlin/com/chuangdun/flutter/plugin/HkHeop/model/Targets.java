package com.chuangdun.flutter.plugin.HkHeop.model;

public class Targets{
    private int id;
    private String targetModelData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTargetModelData() {
        return targetModelData;
    }

    public void setTargetModelData(String targetModelData) {
        this.targetModelData = targetModelData;
    }

    @Override
    public String toString() {
        return "Targets{" +
                "id=" + id +
                ", targetModelData='" + targetModelData + '\'' +
                '}';
    }
}
