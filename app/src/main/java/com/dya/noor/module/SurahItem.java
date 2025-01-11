package com.dya.noor.module;

public class SurahItem {

    private final String aSuratName;
    private final String aSuratIn;
    private final String aSuratIn2;
    private final String aSuratIn4;
    private final String aId;
    private final String aLink;
    private final String aSuranameOth;



    private final String start_page_number;
    private final String end_page_number;

    public SurahItem(String aSuratName, String aSuratIn, String aSuratIn2, String aSuratIn4,
                     String aId, String aLink, String startPageNumber, String endPageNumber, String aSuranameOth) {
        this.aSuratName = aSuratName;
        this.aSuratIn = aSuratIn;
        this.aSuratIn2 = aSuratIn2;
        this.aSuratIn4 = aSuratIn4;
        this.aId = aId;
        this.aLink = aLink;
        start_page_number = startPageNumber;
        end_page_number = endPageNumber;
        this.aSuranameOth = aSuranameOth;

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
    public String getStart_page_number() {
        return start_page_number;
    }

    public String getEnd_page_number() {
        return end_page_number;
    }

    public String getaSuranameOth() {
        return aSuranameOth;
    }
}
