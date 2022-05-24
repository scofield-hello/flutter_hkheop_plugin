package com.lgh.uvccamera;

public class GPYUsbDevice {
    private int pid;
    private int vid;

    public GPYUsbDevice(int pid, int vid) {
        this.pid = pid;
        this.vid = vid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    @Override
    public String toString() {
        return "GPYUsbDevice{" +
                "pid=" + pid +
                ", vid=" + vid +
                '}';
    }
}
