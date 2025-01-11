package com.dya.noor.module;

public class HawallanItem {


    private final String Id;
    private final String Name;
    private final String Disc;

    public HawallanItem(String id, String name, String disc) {
        Id = id;
        Name = name;
        Disc = disc;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDisc() {
        return Disc;
    }
}
