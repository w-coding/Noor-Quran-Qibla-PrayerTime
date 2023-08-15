package com.dya.noor.module;

public class SurahItem {

    private final String aSuratName;
    private final String aSuratIn;
    private final String aSuratIn2;
    private final String aSuratIn4;
    private final String aId;
    private final String aLink;

    public SurahItem(String aSuratName, String aSuratIn, String aSuratIn2, String aSuratIn4,
                     String aId, String aLink) {
        this.aSuratName = aSuratName;
        this.aSuratIn = aSuratIn;
        this.aSuratIn2 = aSuratIn2;
        this.aSuratIn4 = aSuratIn4;
        this.aId = aId;
        this.aLink = aLink;
    }

    public String getaSuratName() {
        return aSuratName;
    }

    public String getaSuratIn() {
        return aSuratIn;
    }

    public String getaSuratIn2() {
        return aSuratIn2;
    }

    public String getaSuratIn4() {
        return aSuratIn4;
    }

    public String getaId() {
        return aId;
    }

    public String getaLink() {
        return aLink;
    }
}
