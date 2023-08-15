package com.dya.noor.module;

public class SearchAyahItem {

    private  String AyahText;
    private  String AyahNumber;

    private  String AyahSearch;

    private  String suratName;
    private  String id;
    private  String link;
    private String page;
    private String juzz;

    public SearchAyahItem(String ayahText,
                          String ayahNumber,
                          String ayahSearch,
                          String suratName,
                          String id,
                          String link,
                          String juzz,
                          String page) {
        AyahText = ayahText;
        AyahNumber = ayahNumber;
        AyahSearch = ayahSearch;
        this.juzz = juzz;
        this.page = page;
        this.suratName = suratName;
        this.id = id;
        this.link = link;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getJuzz() {
        return juzz;
    }

    public void setJuzz(String juzz) {
        this.juzz = juzz;
    }

    public String getSuratName() {
        return suratName;
    }

    public void setSuratName(String suratName) {
        this.suratName = suratName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getAyahSearch() {
        return AyahSearch;
    }

    public void setAyahSearch(String ayahSearch) {
        AyahSearch = ayahSearch;
    }

    public String getAyahText() {
        return AyahText;
    }

    public void setAyahText(String ayahText) {
        AyahText = ayahText;
    }

    public String getAyahNumber() {
        return AyahNumber;
    }

    public void setAyahNumber(String ayahNumber) {
        AyahNumber = ayahNumber;
    }
}
