package com.dya.noor.module;

public class SearchSuraItem {

    private  String suratName;
    private  String id;
    private  String link;

    public SearchSuraItem(String suratName, String id, String link) {
        this.suratName = suratName;
        this.id = id;
        this.link = link;
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
}
