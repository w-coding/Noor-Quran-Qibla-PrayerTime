package com.dya.noor.module;

public class CitiesItem {

    private final String gid;
    private final String iso;
    private final String name;
    private final String NameKurdish;
    private final String NameArabic;
    private final String latitude;
    private final String longitude;
    private final String Jegir;
    private final String DbName;

    public CitiesItem(String gid, String iso, String name, String nameKurdish, String nameArabic, String latitude, String longitude, String jegir, String dbName) {
        this.gid = gid;
        this.iso = iso;
        this.name = name;
        NameKurdish = nameKurdish;
        NameArabic = nameArabic;
        this.latitude = latitude;
        this.longitude = longitude;
        Jegir = jegir;
        DbName = dbName;
    }

    public String getGid() {
        return gid;
    }

    public String getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }

    public String getNameKurdish() {
        return NameKurdish;
    }

    public String getNameArabic() {
        return NameArabic;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getJegir() {
        return Jegir;
    }

    public String getDbName() {
        return DbName;
    }
}
