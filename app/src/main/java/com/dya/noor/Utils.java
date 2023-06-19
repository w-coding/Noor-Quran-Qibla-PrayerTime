package com.dya.noor;

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

        if (date != null && !date.isEmpty()) {
            String monthDay[] = date.split("-");

            cal.set(Calendar.MONTH, Integer.parseInt(monthDay[0]) - 1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(monthDay[1]));
        }

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }


    public static boolean getBooleanPref(String key, boolean def) {
        if (prefs != null) {
            return prefs.getBoolean(key, def);

        }

        return false;
    }
}