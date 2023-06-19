package com.dya.noor;

public class AyahItem {


    private final String Text;
    private final String Ayah;
    private final String Raman;
    private final String Puxta;
    private final String Asan;
    private final String Sanahi;
    private final String Zhin;
    private final String Hazhar;
    private final String Rebar;

    private final String Search;



    public AyahItem(String text, String ayah, String rebar, String raman, String puxta, String asan,
                    String sanahi, String hazhar, String zhin , String search) {
        Text = text;
        Ayah = ayah;
        Raman = raman;
        Puxta = puxta;
        Asan = asan;
        Sanahi = sanahi;
        Zhin = zhin;
        Hazhar = hazhar;
        Rebar = rebar;
        Search = search;
    }
    public String getText() {
        return Text;
    }

    public String getAyah() {
        return Ayah;
    }

    public String getRaman() {
        return Raman;
    }

    public String getPuxta() {
        return Puxta;
    }

    public String getAsan() {
        return Asan;
    }

    public String getSanahi() {
        return Sanahi;
    }

    public String getZhin() {
        return Zhin;
    }

    public String getHazhar() {
        return Hazhar;
    }

    public String getRebar() {
        return Rebar;
    }
    public String getSearch() {
        return Search;
    }

}
