package com.dya.noor.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class Utils {
    public static Context activeContext;

    public static SharedPreferences prefs;

    public Utils(Context ct) {
        activeContext = ct;

        registerPrefs(activeContext);
    }

    public static void registerPrefs(Context con) {
        if (con == null) {
            con = activeContext;
        }

        try {
            prefs = PreferenceManager.getDefaultSharedPreferences(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void put(String key, Object val) {
        if (prefs != null) {

            if (val instanceof Integer) {
                prefs.edit().putInt(key, (int) val).apply();
            } else if (val instanceof String) {
                prefs.edit().putString(key, val.toString()).apply();
            } else if (val instanceof Long) {
                prefs.edit().putLong(key, (Long) val).apply();
            } else if (val instanceof Boolean) {
                prefs.edit().putBoolean(key, (Boolean) val).apply();
            } else if (val instanceof Float) {
                prefs.edit().putFloat(key, (Float) val).apply();
            }
        } else {
            registerPrefs(Utils.activeContext);
            put(key, val);
        }
    }

    public static float getFloatPref(String key, float def) {
        if (prefs != null) {
            return prefs.getFloat(key, def);
        }

        return -1;
    }

    public static int getIntegerPref(String key, int def) {
        if (prefs != null) {
            return prefs.getInt(key, def);
        }

        return -1;
    }

    public static long getLongPref(String key, long def) {
        if (prefs != null) {
            return prefs.getLong(key, def);
        }

        return -1;
    }

    public static String getStringPref(String key, String def) {

        if (prefs != null) {
            return prefs.getString(key, def);
        }

        return null;
    }

    public static void removeKey(String key) {
        if (prefs.contains(key)) {
            prefs.edit().remove(key).commit();
        }
    }


    public static String formatSecondsToTimeKurdish(long seconds) {
        if (seconds <= 0) {
            return "";
        }
        long h = seconds / 3600;
        long m = seconds % 3600 / 60;
        long s = seconds % 60; // Less than 60 is the second, enough 60 is the minute

        String finalText = "";

        if (h > 0) {
            finalText += h + " ك ";
        }

        if (m > 0) {
            finalText += m + " خ ";
        }

        if (s > 0) {
            finalText += s + " چ ";
        }

        return finalText;
    }

    public static String replaceEasternNumbers(Object original) {
        //for arabic
        original = original.toString().replaceAll("١", "1")
                .replaceAll("٢", "2")
                .replaceAll("٣", "3")
                .replaceAll("٤", "4")
                .replaceAll("٥", "5")
                .replaceAll("٦", "6")
                .replaceAll("٧", "7")
                .replaceAll("٨", "8")
                .replaceAll("٩", "9")
                .replaceAll("٠", "0");

        //for persian
        original = original.toString().replaceAll("۱", "1")
                .replaceAll("۲", "2")
                .replaceAll("۳", "3")
                .replaceAll("۴", "4")
                .replaceAll("۵", "5")
                .replaceAll("۶", "6")
                .replaceAll("۷", "7")
                .replaceAll("۸", "8")
                .replaceAll("۹", "9")
                .replaceAll("۰", "0");

        return original.toString();
    }


    public static String replaceEnglishNumbers(Object original) {
        return original.toString().replaceAll("1", "١")
                .replaceAll("2", "٢")
                .replaceAll("3", "٣")
                .replaceAll("4", "٤")
                .replaceAll("5", "٥")
                .replaceAll("6", "٦")
                .replaceAll("7", "٧")
                .replaceAll("8", "٨")
                .replaceAll("9", "٩")
                .replaceAll("0", "٠");
    }


    public static long getMilliFromTextedTime(String date, String time) {

        int hour = Integer.parseInt(time.substring(0, time.indexOf(":")));
        int minute = Integer.parseInt(time.substring(time.indexOf(":") + 1));

        Calendar cal = Calendar.getInstance();

        //Log.d("HAX", String.format("date = %d/%d/%d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)));

        if (date != null && !date.isEmpty()) {
            String monthDay[] = date.split("-");

            cal.set(Calendar.MONTH, Integer.parseInt(monthDay[0]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(monthDay[1]));
        }

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        //mang w rozhi esta
        int curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int curDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        //mang w rozh bo aw bangai wari agrin
        int calculatedMonth = cal.get(Calendar.MONTH) + 1;
        int calculatedDay = cal.get(Calendar.DAY_OF_MONTH);

        /** Bo Keshai Sali dwatr (31/12) :
         Agar barwari esta 12/31 bw
         balam barwari bangakai wari agrin 1/1 bw
         yak sall ziad bka bo barwari bangaka ta kesha drwst nabet la millisecond.
         am marja la yak kat run abet, awish dwai bangi Isha bo 12/31

         La kati diari krdni bangi dahatw ema waha list drwst akain
         kati : [ bayani, niwaro, asr, eywara, esha, bayani (rozhi dwatr)]

         dwatr esta barawrd akain ba millisecond har yakekian, har kamian gawratr bw la esta
         warai waya bangi dahatw awayana.
         balam ema la hich kwe bo bangakan sal diari nakain, la database'ish
         barwarakan tanha rozh w mangn.
         boia bo 31/12 katek bangi bayani rozhi dwatr war agrin aw tanha rozh
         agoret bo 1/1
         wata listaka waha abet :
         [2023/12/31 bayani, 2023/12/31 niwaro, 2023/12/31 asr, 2023/12/31 eywara, 2023/12/31 isha, 2023/1/1 bayani]
         lam if xwarawa alein agar barwari esta 31/12 bw, balam aw bangaki dawai akain 1/1 bw
         (wata tanha bo kota bangi bayani kota sall)
         awa tanha salli aw barwarai dahatw 1 salli bxa sar (ta 2023/1/1 bkat ba 2024/1/1)
         waha millisecond gawratr dar achet ba barawrd ba isha pesh xoi


         */

        if (curMonth == 12 && curDay == 31 &&
                calculatedDay == 1 && calculatedMonth == 1) {
            cal.add(Calendar.YEAR, 1);
        }

        return cal.getTimeInMillis();
    }


    public static boolean getBooleanPref(String key, boolean def) {
        if (prefs != null) {
            return prefs.getBoolean(key, def);

        }

        return false;
    }
}