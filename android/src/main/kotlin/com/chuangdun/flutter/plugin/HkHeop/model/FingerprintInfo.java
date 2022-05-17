package com.chuangdun.flutter.plugin.HkHeop.model;

public class FingerprintInfo {
    private int step;
    private boolean finish;
    private String fpTemplate;
    private String fpPic;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getFpTemplate() {
        return fpTemplate;
    }

    public void setFpTemplate(String fpTemplate) {
        this.fpTemplate = fpTemplate;
    }

    public String getFpPic() {
        return fpPic;
    }

    public void setFpPic(String fpPic) {
        this.fpPic = fpPic;
    }

    @Override
    public String toString() {
        return "IDCardInfo{" +
                "step='" + step + '\'' +
                ", finish='" + finish + '\'' +
                ", fpTemplate='" + fpTemplate + '\'' +
                ", fpPic='" + fpPic +
                '}';
    }
}
