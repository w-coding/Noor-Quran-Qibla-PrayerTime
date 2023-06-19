package com.dya.noor;

public class SearchFarmwdaItem {

    private String Id;
    private String name;
    private String Ar_Desc;
    private String Ku_Desc;

    public SearchFarmwdaItem(String id,String name, String ar_Desc, String ku_Desc) {
        Id = id;
        this.name=name;
        Ar_Desc = ar_Desc;
        Ku_Desc = ku_Desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAr_Desc() {
        return Ar_Desc;
    }

    public void setAr_Desc(String ar_Desc) {
        Ar_Desc = ar_Desc;
    }

    public String getKu_Desc() {
        return Ku_Desc;
    }

    public void setKu_Desc(String ku_Desc) {
        Ku_Desc = ku_Desc;
    }
}
