package com.chuangdun.flutter.plugin.HkHeop.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public
class Jsonlog {
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "json")
    private String json;

    @PrimaryKey(autoGenerate = true)
    int id = 0;

    public Jsonlog(String status, String json){
        this.json = json;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Jsonlog{" +
                "status='" + status + '\'' +
                ", json='" + json + '\'' +
                ", id=" + id +
                '}';
    }
}
