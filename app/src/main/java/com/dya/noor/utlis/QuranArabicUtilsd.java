package com.dya.noor.utlis;


public  class QuranArabicUtilsd {


    //gunnahmatcher matches the letters noon sakinah or tanween followed by the letter ya'.
    public static final java.util.regex.Matcher gunnahmatcher = java.util.regex.Pattern.compile("[\u0646|\u0645]\u0651").matcher("");
    //idhghammatcher matches the letters noon sakinah or tanween followed by any of the letters lam, mim, or ra'.
    public static final java.util.regex.Matcher idhghammatcher = java.util.regex.Pattern.compile("([\u0646\u064b\u064c\u064d][\u0652\u0627\u0649]?[\u06db\u06da\u06d7\u06d6\u06d9\u06d8]? [\u0646\u0645\u064a\u0648])|\u0645[\u06db\u06da\u06d7\u06d6\u06d9\u06d8\u0652]? \u0645").matcher("");
    //idhghammatcherwihtoutgunnah matches the letters noon sakinah or tanween followed by the letter ha'.
    public static final java.util.regex.Matcher idhghammatcherwihtoutgunnah = java.util.regex.Pattern.compile("[\u0646\u064d\u064b\u064c][\u0652\u0627\u0649]?[\u06db\u06da\u06d7\u06d6\u06d9\u06d8]? [\u0631\u0644]").matcher("");
    //ikhfamatcher matches the letters noon sakinah or tanween followed by any of the letters baa', ta', tha', jim, ha', kha', dal, dhal, thal, ra', zay, or noon.
    public static final java.util.regex.Matcher ikhfamatcher = java.util.regex.Pattern.compile("([\u0646\u064d\u064b\u064c][\u0652\u0627\u0649]?[\u06db\u06da\u06d7\u06d6\u06d9\u06d8]? ?[\u0635\u0630\u062b\u0643\u062c\u0634\u0642\u0633\u062f\u0637\u0632\u0641\u062a\u0636\u0638])|\u0645\u0652? ?\u0628").matcher("");
   //iqlabmmatcher matches the letters noon sakinah or tanween followed by the letter ba'.
    public static final java.util.regex.Matcher iqlabmmatcher = java.util.regex.Pattern.compile("[\u06e2\u06ed][\u0652\u0627\u0649]?[\u06db\u06da\u06d7\u06d6\u06d9\u06d8]? ?\u0628").matcher("");
    //qalqalamatcher matches the letters lam, ra', or shadda followed by any of the letters baa', ta', tha', jim, ha', kha', dal, dhal, thal, ra', zay, or noon.
    public static final java.util.regex.Matcher qalqalamatcher = java.util.regex.Pattern.compile("[\u0642\u0637\u0628\u062c\u062f](\u0652|[^\u0647]?[^\u0647\u0649\u0627]?[^\u0647\u0649\u0627]$)").matcher("");
    public static int[] colors;


    public static android.text.Spannable getTajweed(String s, android.content.Context context) {


        colors = new int[6];
        colors[0]=(android.graphics.Color.argb(255,209,106,0));//R.color.color_ghunna_Red
        colors[1]=(android.graphics.Color.argb(255,0,120,0));//R.color.color_qalqala_Purple
        colors[2]=(android.graphics.Color.argb(255,0,75,214));//R.color.color_iqlab
        colors[3]=(android.graphics.Color.argb(255,185,84,200));//R.color.color_idgham_Blue
        colors[4]=(android.graphics.Color.argb(255,0,193,193));// R.color.color_IdghamwithoutGunnah_Yellow
        colors[5]=(android.graphics.Color.argb(255,182,0,0));//R.color.color_ikhfa_Green


        android.text.Spannable text = new android.text.SpannableString(s);
        gunnahmatcher.reset(s);
        while (gunnahmatcher.find()) {
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[0]), gunnahmatcher.start(), gunnahmatcher.end() + 1, 0);
        }
        qalqalamatcher.reset(s);
        while (qalqalamatcher.find()) {
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[1]), qalqalamatcher.start(), qalqalamatcher.end(), 0);
        }
        iqlabmmatcher.reset(s);
        while (iqlabmmatcher.find()) {
            android.util.Log.d("iqlab Found text " + iqlabmmatcher.group(), "starting at " + iqlabmmatcher.start() + " and ending at " + iqlabmmatcher.end());
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[2]), getIqlabStart(s, iqlabmmatcher.start()), iqlabmmatcher.end() + 1, 0);
        }
        idhghammatcher.reset(s);
        while (idhghammatcher.find()) {
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[3]), getStart(s, idhghammatcher.start()), getEnd(s, idhghammatcher.end()), 0);
        }
        idhghammatcherwihtoutgunnah.reset(s);
        while (idhghammatcherwihtoutgunnah.find()) {
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[4]), getStart(s, idhghammatcherwihtoutgunnah.start()), getEnd(s, idhghammatcherwihtoutgunnah.end()), 0);
        }
        ikhfamatcher.reset(s);
        while (ikhfamatcher.find()) {
            text.setSpan(new android.text.style.ForegroundColorSpan(colors[5]), getStart(s, ikhfamatcher.start()), getEnd(s, ikhfamatcher.end()), 0);
        }
        return text;
    }

    public static int getIqlabStart(String m, int start) {
        int i = 1;
        int i2 = (m.charAt(start + -1) == '\u064b' ? 1 : 0) | (m.charAt(start + -1) == '\u064c' ? 1 : 0);
        if (m.charAt(start - 1) != '\u064d') {
            i = 0;
        }
        if ((i2 | i) == 0) {
            return start - 1;
        }
        if (m.charAt(start - 2) == '\u0651') {
            return start - 3;
        }
        return start - 2;
    }

    public static int getEnd(String m, int end) {
        if (m.charAt(end) == '\u0651') {
            return end + 2;
        }
        return end + 1;
    }

    public static int getStart(String m, int start) {
        int i;
        int i2 = 1;
        if (m.charAt(start) == '\u064b') {
            i = 1;
        } else {
            i = 0;
        }
        i |= m.charAt(start) == '\u064c' ? 1 : 0;
        if (m.charAt(start) != '\u064d') {
            i2 = 0;
        }
        if ((i | i2) == 0) {
            return start;
        }
        if (m.charAt(start - 1) == '\u0651') {
            return start - 2;
        }
        return start - 1;
    }
}