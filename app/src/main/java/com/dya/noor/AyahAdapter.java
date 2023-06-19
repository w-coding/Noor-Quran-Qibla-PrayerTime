package com.dya.noor;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class AyahAdapter extends RecyclerView.Adapter<AyahAdapter.MyViewHolder> implements Filterable {

   // ArrayList   aText , aNum, aRaman , aPuxta , aAsan  , aSanahi ,aZhin ,aHazhar , aRebar ;
    Context context;
    boolean activate = true;
    String SurahNAme ,SurahId , SurahLink ;
    List<AyahItem> searchText1;
    List<AyahItem> aText;


    public AyahAdapter(Context ct   , List<AyahItem> iText ){

        context = ct;
        aText = iText;
        this.searchText1 = new ArrayList<>(iText);

        new Utils(ct);



    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_ayah_row,parent,false);
        return new MyViewHolder(view);
    }





    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AyahItem itemData =aText.get(position);


        String itemPosition = itemData.getAyah();




        holder.btnSave.setOnClickListener(v -> {


            // Save the position to shared preferences
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("recycler_view_position", itemPosition);
            editor.putString("suraName", SurahNAme);
            editor.putString("suraId", SurahId);
            editor.putString("suraLink", SurahLink);
            editor.apply();
            Toast.makeText(context, "کۆتا خوێندنەوە ئایەتی : "+itemPosition+"  لە "+SurahNAme, Toast.LENGTH_SHORT).show();
        });

        String ab =itemData.getText()
                .replaceAll("1", "١")
                .replaceAll("2", "٢")
                .replaceAll("3", "٣")
                .replaceAll("4", "٤")
                .replaceAll("5", "٥")
                .replaceAll("6", "٦")
                .replaceAll("7", "٧")
                .replaceAll("8", "٨")
                .replaceAll("9", "٩")
                .replaceAll("0", "٠");

        // reverse string
        StringBuffer buffer = new StringBuffer(ab);
        buffer.reverse();
       // holder.mNum.setText(ab);
        holder.mNum.setText(itemData.getAyah());
       // NumberFormat ArNum = NumberFormat.getInstance(Locale.forLanguageTag("AR"));
      //  holder.mNum.setText(ArNum.format(aText) +"\u06DD");
        //holder.mAyah.setText(itemData.getText());
        // TUDO  بۆتەجویید
        holder.mAyah.setText(QuranArabicUtilsd.getTajweed(itemData.getText(),Utils.activeContext));

        holder.LinearLayout.setOnClickListener(v -> {

            BottomSheetDialog dialog = new BottomSheetDialog(v.getContext(),R.style.BottomSheetStyle);
            dialog.setContentView(R.layout.bottomshet_dialog_tafser);
            TextView tvH = dialog.findViewById(R.id.tvHAyah);
            TextView tvHName = dialog.findViewById(R.id.tvHName);
            TextView tvHAyahAr = dialog.findViewById(R.id.tvHAyahAr);
            ImageButton btnCopyAll = dialog.findViewById(R.id.btnCopyAll);

            // Show the BottomSheetDialog.
            if (Utils.getStringPref("help", "").equals("raman"))
            {
                tvH.setText(itemData.getRaman());
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری ڕامان");
                dialog.show();

            }

          else if (Utils.getStringPref("help", "").equals("puxta")) {
                tvH.setText(itemData.getPuxta());
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری پوختە");
                dialog.show();


            }

            else if (Utils.getStringPref("help", "").equals("asan")) {
                tvH.setText(itemData.getAsan());
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری ئاسان");
                dialog.show();
            }


            else if (Utils.getStringPref("help", "").equals("sanahi")) {
                tvH.setText(itemData.getSanahi());
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری سەناهی");
                dialog.show();
            }


            else if (Utils.getStringPref("help", "").equals("zhin")) {

                tvH.setText(itemData.getZhin());
                tvHName.setText("تەفسیری ژیر");
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                dialog.show();

            }


            else if (Utils.getStringPref("help", "").equals("hazhar")) {
                tvH.setText(itemData.getHazhar());
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری هەژار");
                dialog.show();
            }

            else if (Utils.getStringPref("help", "").equals("rebar")){
                tvH.setText(itemData.getRebar().replaceAll("<br>", "\t"));
                tvHAyahAr.setText(itemData.getText() + "(" +itemData.getAyah()+")");
                tvHName.setText("تەفسیری ڕێبەر");
                dialog.show();
            }


            else if (Utils.getStringPref("help", "").equals("hich")) {
                Toast.makeText(context, "هیچ تەفسیرێک دیاری نەکراوە!", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(context, "هیچ تەفسیرێک دیاری نەکراوە!", Toast.LENGTH_SHORT).show();
            }

            btnCopyAll.setOnClickListener(v1 -> {

                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(
                        Context.CLIPBOARD_SERVICE
                );
                ClipData clipData;


        if (Utils.getStringPref("help", "").equals("raman")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }

        if (Utils.getStringPref("help", "").equals("puxta")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }

        if (Utils.getStringPref("help", "").equals("asan")) {
            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }


        if (Utils.getStringPref("help", "").equals("sanahi")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();


        }


        if (Utils.getStringPref("help", "").equals("zhin")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }


        if (Utils.getStringPref("help", "").equals("hazhar")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }

        if (Utils.getStringPref("help", "").equals("rebar")) {

            clipData = ClipData.newPlainText("text", tvHAyahAr.getText()+"\n" + tvH.getText()+"\n"+"\n"+SurahNAme+"\n"+"\n"+"["+ tvHName.getText()+"]"+"\n"+"\n"+"#ئەپڵیکەیشنی_نور");
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();

        }

            });


        });
        holder.btnCopy.setOnClickListener(view -> {
            ClipboardManager clipboardManager2 = (ClipboardManager) context.getSystemService(
                    Context.CLIPBOARD_SERVICE
            );
            ClipData clipData2 = ClipData.newPlainText("text", itemData.getAyah() +" ( "+ itemData.getText()+" ) "+"\n"+"\n"+"<<"+SurahNAme+">>"+"\n"+"#ئەپڵیکەیشنی_نور "+"\n"+"\n");
            clipboardManager2.setPrimaryClip(clipData2);
            Toast.makeText(context, "کۆپی بوو", Toast.LENGTH_SHORT).show();


        });

    }

    @SuppressLint("NotifyDataSetChanged")
    public void  activateButton(boolean activate){

        this.activate =activate;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return aText.size();

    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List <AyahItem> filterList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filterList.addAll(searchText1);
            }else {

                for (AyahItem text : searchText1 ){
                    if (text.getSearch().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(text);
                    } else if (text.getAyah().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filterList.add(text);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            aText.clear();
            aText.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mAyah;
        TextView mNum;
        ImageButton btnCopy , btnSave;
        ConstraintLayout LinearLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mAyah= itemView.findViewById(R.id.qurann);
            mNum= itemView.findViewById(R.id.ayanum);

            btnCopy = itemView.findViewById(R.id.btnCopy);
            btnSave = itemView.findViewById(R.id.btnSave);
            LinearLayout = itemView.findViewById(R.id.ayaLayout);


        }
    }



}