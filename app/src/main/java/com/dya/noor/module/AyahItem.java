package com.dya.noor.module;

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
    private final String Tawhid;
    private final String Roshn;
    private final String Maisar;



    public AyahItem(String text, String ayah, String rebar, String raman, String puxta, String asan,
                    String sanahi, String hazhar, String zhin , String search , String tawhid, String roshn,String maisar) {
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
        Tawhid = tawhid;
        Roshn = roshn;
        Maisar = maisar;
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
    public String getTawhid() {
        return Tawhid;
    }
    public String getRoshn() {
        return Roshn;
    }
    public String getMaisar() {
        return Maisar;
    }

}
