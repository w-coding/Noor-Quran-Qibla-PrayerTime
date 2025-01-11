package com.dya.noor.database;

public class PrayerInfo {

    public String prayerName, prayerNameKurdish;
    public long milli;

    int prayerIndex;

    public PrayerInfo(int which, long milli) {
        prayerIndex = which;
        switch (which) {
            case 5:
            case 0:
                this.prayerName = "bayani";
                this.prayerNameKurdish = "بەیانی";
                break;
            case 1:
                this.prayerName = "niwaro";
                this.prayerNameKurdish = "نیوەڕۆ";
                break;
            case 2:
                this.prayerName = "asr";
                this.prayerNameKurdish = "عەسر";
                break;
            case 3:
                this.prayerName = "eywara";
                this.prayerNameKurdish = "ئێوارە";
                break;
            case 4:
                this.prayerName = "esha";
                this.prayerNameKurdish = "خەوتنان";
                break;
        }

        this.milli = milli;
    }

    @Deprecated // Bakari mahena
    public PrayerInfo(String prayerName, long milli) {
        this.prayerName = prayerName;
        this.milli = milli;
    }

}
