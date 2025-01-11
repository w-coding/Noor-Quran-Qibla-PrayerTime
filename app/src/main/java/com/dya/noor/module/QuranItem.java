package com.dya.noor.module;

public class QuranItem {
    
    private final String aya_text;
    private final String aya_number;
    private final String sura_number;
    private final String aya_text_emlaey;
    private final String jozz;
    private final String page;
    private final String aya_number_arabic;
    private final String aya_number_arabic_rev;

    public String getAya_number_arabic_rev() {
        return aya_number_arabic_rev;
    }

    public String getAya_number_arabic() {
        return aya_number_arabic;
    }

    private final String sura_name;

    public String getSura_name() {
        return sura_name;
    }
    public String getAya_text() {
        return aya_text;
    }
    
    public String getSuraNumber() {
        return sura_number;
    }
    
    public String getAya_number() {
        return aya_number;
    }
    
    public String getAya_text_emlaey() {
        return aya_text_emlaey;
    }


    
    public String getJozz() {
        return jozz;
    }
    
    public String getPage() {
        return page;
    }
    
    public int getPagAsInt() {
        if (page == null || page.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(page.trim());
    }
    public int getJozzAsInt() {
        if (jozz == null || jozz.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(jozz.trim());
    }
    
    public QuranItem(String aya_text, String sura_number, String aya_number, String aya_text_emlaey, String jozz, String page, String sura_name, String ayaNumberArabic, String ayaNumberArabicRev) {
        this.aya_text = aya_text;
        this.sura_number = sura_number;
        this.aya_number = aya_number;
        this.aya_text_emlaey = aya_text_emlaey;
        this.jozz = jozz;
        this.page = page;
        aya_number_arabic = ayaNumberArabic;
        aya_number_arabic_rev = ayaNumberArabicRev;
        this.sura_name = sura_name;
    }
}
