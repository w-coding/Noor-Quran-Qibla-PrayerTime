package com.dya.noor.database;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dya.noor.notifications.AlarmReceiver;
import com.dya.noor.utlis.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressLint("Range")
public class MydbClass extends SQLiteOpenHelper {
    public static String dbName = "nor_database_v12.db";
    public static int dbVersion = 2;
    public static String dbPath ="";
    public static Context myContext;
    //new
    public MydbClass(@Nullable Context context) {
        super(context,dbName,null,dbVersion);
        myContext=context;
        StartWork();
    }



    public static ArrayList<Long> getPrayerForDate(String date) {
        return getPrayerForDate(date, false);
    }


    public static ArrayList<Long> getPrayerForDate(String date, boolean includeSunrise) {
        ArrayList<Long> prayers = new ArrayList<>();

        try {
            MydbClass db = new MydbClass(Utils.activeContext);

            Cursor prayerCursor = db.readAllDate(getSelectedCity(), date);

            prayerCursor.moveToNext();

            prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("bayani"))));

            if (includeSunrise) {
                prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("xorhalatn"))));
            }

            prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("niwaro"))));

            prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("asr"))));

            prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("eywara"))));

            prayers.add(Utils.getMilliFromTextedTime(date, prayerCursor.getString(prayerCursor.getColumnIndex("esha"))));
        } catch (Exception e) {
            Log.e("-HAX-", "error in fetching prayer time ", e);
        }
        return prayers;
    }

    public static ArrayList<Long> getPrayerWithDateAdded(int add_day) {
        return getPrayerWithDateAdded(add_day, false);
    }

    @SuppressLint("DefaultLocale")
    public static ArrayList<Long> getPrayerWithDateAdded(int add_day, boolean includeSunrise) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, add_day);

        int month = cal.get(Calendar.MONTH) + 1;




        //Log.d("HAX",month+"/"+  cal.get(Calendar.DAY_OF_MONTH));

        return getPrayerForDate(String.format("%02d-%02d",
                        month,
                        cal.get(Calendar.DAY_OF_MONTH)),
                includeSunrise);
    }

    public static ArrayList<Long> getTodayPrayers() {
        return getPrayerWithDateAdded(0);
    }

    public static ArrayList<Long> getTodayPrayers(boolean includeSunrise) {
        return getPrayerWithDateAdded(0, includeSunrise);
    }

    public static PrayerInfo getNextPrayer() {
        ArrayList<Long> prayers = getTodayPrayers();

        //bangi bayani sbaine
        prayers.add(getPrayerWithDateAdded(1).get(0));

        for (int i = 0; i < prayers.size(); i++) {
            if (prayers.get(i) > System.currentTimeMillis()) {
                return new PrayerInfo(i, prayers.get(i));
            }
        }

        Log.d("HAX","didn't pick any prayer. returning null.");

        return null;
    }

    public static void setNextPrayer(Context context) {

        if (Utils.activeContext == null
                && context != null) {
            Utils.activeContext = context;
        }

        Calendar cal = Calendar.getInstance();
        PrayerInfo pr = getNextPrayer();

        //Log.d("HAX","pName="+pr.prayerName);

        cal.setTimeInMillis(pr.milli);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent in = new Intent(context, AlarmReceiver.class);
        in.putExtra("what_prayer", pr.prayerName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                762001,
                in,
                PendingIntent.FLAG_MUTABLE);

        long triggerTime = cal.getTimeInMillis();

        if (android.os.Build.VERSION.SDK_INT < 23) {
            if (android.os.Build.VERSION.SDK_INT >= 19) {
                am.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            } else {
                am.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            }
        } else {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
        }

        Log.d("HAX", pr.prayerName + "-timer-set-for=" + cal.getTimeInMillis());
    }

    public static PrayerInfo getCurrentClosestPrayer() {
        ArrayList<Long> main = getTodayPrayers();
        ArrayList<Long> prTimesMilli = new ArrayList<>();
// bo yakjar sayry awash nakay kat agata 31y 12  awa error adat la dway bangy isha wa la mili sacnd
        prTimesMilli.addAll(main);

        long now = System.currentTimeMillis();

        for (int i = 0; i < prTimesMilli.size(); i++) {
            prTimesMilli.set(i, Math.abs(now - prTimesMilli.get(i)));
        }

        int chosen = 0;
        for (int i = 0; i < prTimesMilli.size(); i++) {
            //we find the smallest value [closest time] to current phone time.
            if (prTimesMilli.get(i) < prTimesMilli.get(chosen)) {
                chosen = i;
            }
        }

        return new PrayerInfo(chosen, main.get(chosen));
    }

    public static long getNextPrayerMilli() {
        return (getNextPrayer()).milli;
    }

    public static String getSelectedCity() {
        SharedPreferences pref = Utils.activeContext.getSharedPreferences("key", Context.MODE_PRIVATE);
        return pref.getString("City", "Kalar");
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean ExistDatabase(){
        File myFile = new File(dbPath+dbName);

        return myFile.exists();
    }
    private static void CopyDataBase(Context context){

        try {
            InputStream myInput = context.getAssets().open(dbName);
            OutputStream myOutput =new FileOutputStream(dbPath+dbName);
            byte [] myBuffer = new byte[1024];
            int length;
            while ((length = myInput.read(myBuffer))>0){
                myOutput.write(myBuffer,0,length);
            }
            myOutput.flush(); myOutput.close(); myInput.close();
        }catch (Exception ex){

        }
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        db.disableWriteAheadLogging();

        //TODO : charasari keshai .WAL w .SHM
       /* try {
            //this removes the .WAL file which is created in older versions of android ( version <= 9)
            //the .WAL acts as a transaction list which holds the changes made to the database, below line
            //takes all the transactions back to the original database.
            db.rawQuery("PRAGMA wal_checkpoint;", null);
            //this one removes the .WAL and .SHM files
            db.rawQuery("PRAGMA journal_mode=Delete;", null);

            //this disables the .WAL and .SHM from being created moving forward.
            db.disableWriteAheadLogging();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        super.onOpen(db);
    }

    public void StartWork(){

        dbPath = myContext.getFilesDir().getParent()+"/databases/";
        // dbPath = myContext.getDatabasePath(dbName).getPath();
        if (!ExistDatabase()){
            this.getReadableDatabase();
            CopyDataBase(myContext);
        }

    }

    public Cursor readAllData(){
        String query ="select * from surat";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

    public Cursor readAllZikrData(){
        String query ="select * from dhikrname";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }
    public Cursor readAllAllahName(){
        String query ="select * from names";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

    public Cursor readAllAyahData(String id){

        String query ="select * from ayah_text WHERE suraId ="+id+" ORDER BY ayah ASC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;

    }
    public Cursor readAllqurannewData(){

        String query ="select suraId ,juzz,page,link,sura_name_ar,ayah,text,search from ayah_text  ORDER BY page ASC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;

    }

    public Cursor readAllFarmudaTData(){
        String query ="select _id,titel from farmuda";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }
    public Cursor readAllFarmudaData(String id){

        String query ="select * from farmuda WHERE _id ="+id+"";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;

    }

    public Cursor readAllFarmudaDataSearch(){

        String query ="select * from farmuda ORDER BY _id ASC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;

    }


    public Cursor readAllCities(String countryCode){

        String query ="select * from cities WHERE country_code ="+countryCode;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }





    Cursor readAllDate(String City, String Date) {

        String query = "select * from " + City + " WHERE D ='" + Utils.replaceEasternNumbers(Date)  + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }


   public Cursor readAllDataZikr(String id){

        String query ="select * from dhikr WHERE dhikrid ="+id+" ORDER BY id ASC";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

}

