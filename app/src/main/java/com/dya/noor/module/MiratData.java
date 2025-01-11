package com.dya.noor.module;

public class MiratData {

    private final String Id;
    private final String Title;
    private final String Disc;

    public MiratData(String id, String title, String disc) {
        Id = id;
        Title = title;
        Disc = disc;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDisc() {
        return Disc;
    }
}
