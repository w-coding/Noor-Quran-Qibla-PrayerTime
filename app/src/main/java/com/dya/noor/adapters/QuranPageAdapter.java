package com.dya.noor.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.module.QuranItem;
import com.dya.noor.ui.QuranPage;

import java.util.ArrayList;
import java.util.List;

public class QuranPageAdapter extends RecyclerView.Adapter<QuranPageAdapter.QuranViewHolder> {

    Context context;
    public String SurahNAme, SurahId;
    List<Integer> arPageNumbers;

    public static String suraName;
    public static int suraId;

    public static boolean isSubItemVisible;


    private OnButtonClickListener listener;

    QuranItem currentAyah = null;
    StringBuffer buffer;
    String reversedBuffer;
    int numberIndexStart;
    int numberIndexEnd;


    public QuranPageAdapter(Context context, ArrayList<Integer> arPageNumbers ,OnButtonClickListener listener ) {
        this.context = context;
        this.arPageNumbers = arPageNumbers;
        this.listener=listener;
    }
    
    @NonNull
    @Override
    public QuranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quran_page_row, parent, false);
        return new QuranViewHolder(view);
    }
    
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuranViewHolder holder, int position) {
        SharedPreferences preferences = context.getSharedPreferences("myPreferences", MODE_PRIVATE);
        isSubItemVisible=preferences.getBoolean("isSubItemVisible", false);

        int currentPage = arPageNumbers.get(position);

        ArrayList<QuranItem> arAyahForPage = QuranPage.mpAllAyah.get(currentPage);
        
        StringBuilder allText = new StringBuilder("");


        // datay ayatakani har paraiak ziad akain bo ayahText
        for (QuranItem qt : arAyahForPage) {

            if (!isSubItemVisible) {
                allText.append(qt.getAya_text() + " "+qt.getAya_number_arabic());
            } else {
                allText.append(qt.getAya_text() + " "+qt.getAya_number_arabic_rev());
                }

        }
        
        Spannable span = Spannable.Factory.getInstance().newSpannable(allText);

        // pedani eshi click
        for (QuranItem ayah : arAyahForPage) {
            String ayahText = ayah.getAya_text();
            
            int indexStart = allText.toString().indexOf(ayahText);
            int indexEnd = indexStart + ayahText.length();
            
            // All the rest will have the same spannable.
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(View v) {

                    /**

                    String ayahNumber = ayah.getAya_number();
                   // String ayahTafseer = ayah.getTafseer();
                    String ayahTextEmla = ayah.getAya_text_emlaey();
                    currentAyah = ayah;

                    // atawet chi bkat ka click lam ayata kra lera binwsa.

                    BottomSheetDialog dialog = new BottomSheetDialog(v.getContext(), R.style.BottomSheetStyle);
                    dialog.setContentView(R.layout.bottomshet_dialog_tafser);

                    TextView tvH = dialog.findViewById(R.id.tvHAyah);
                    TextView tvHName = dialog.findViewById(R.id.tvHName);
                    TextView tvHAyahAr = dialog.findViewById(R.id.tvHAyahAr);

                    ImageButton btnCopyAll = dialog.findViewById(R.id.btnCopyAll);

                    ImageButton btnForward = dialog.findViewById(R.id.btnForward);
                    ImageButton btnBackward = dialog.findViewById(R.id.btnBackward);
                    int currentAyahIndex = arAyahForPage.indexOf(currentAyah);
                    // Show the BottomSheetDialog.

                   // tvH.setText(ayahTafseer);
                    tvHAyahAr.setText(ayahTextEmla + "(" + ayahNumber + ")");
                    tvHName.setText("Tafsir IbnKatheer");
                    dialog.show();
                    // eshi click
                  //  Toast.makeText(context, "Clicked " + surahNumber + ":" + ayahNumber, Toast.LENGTH_SHORT).show();
                    // -----
                    // v.invalidate();


                    AtomicInteger nextAyahIndex = new AtomicInteger(currentAyahIndex);

                   // int nextAyahIndex = currentAyahIndex +1;


                    btnForward.setOnClickListener(v1 -> {

                        int position2 = nextAyahIndex.get();

                        if (position2 < arAyahForPage.size() - 1) {
                            // Handle reaching the end of the page (e.g., move to the next page)
                            nextAyahIndex.getAndIncrement();
                            position2 = nextAyahIndex.get();
                            QuranItem nextAyah = arAyahForPage.get(position2);
                           // tvH.setText(nextAyah.getTafseer());
                            tvHAyahAr.setText(nextAyah.getAya_text_emlaey() + "(" + nextAyah.getAya_number() + ")");
                            tvHName.setText("Tafsir IbnKatheer");
                            dialog.show();
                        }

                    });


                    btnBackward.setOnClickListener(v1 -> {

                        int position3 = nextAyahIndex.get();

                        if (position3 >0) {
                            // Handle reaching the end of the page (e.g., move to the next page)
                            nextAyahIndex.decrementAndGet();
                            position3 = nextAyahIndex.get();
                            QuranItem nextAyah = arAyahForPage.get(position3);
                           // tvH.setText(nextAyah.getTafseer());
                            tvHAyahAr.setText(nextAyah.getAya_text_emlaey() + "(" + nextAyah.getAya_number() + ")");
                            tvHName.setText("Tafsir IbnKatheer");
                            dialog.show();
                        }

                    });
                    btnCopyAll.setOnClickListener(v1 -> {

                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(
                                Context.CLIPBOARD_SERVICE
                        );
                        ClipData clipData;
                        clipData = ClipData.newPlainText("text", tvHAyahAr.getText() + "\n" + tvH.getText() + "\n" + "\n" + SurahNAme + "\n" + "\n" + "#Tafsir_IbnKatheer");
                        clipboardManager.setPrimaryClip(clipData);
                        Toast.makeText(context, "coped", Toast.LENGTH_SHORT).show();


                    });

                */
                }


                
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };
            
            span.setSpan(cs, indexStart, indexEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        suraName = arAyahForPage.get(0).getSura_name();
        holder.lblPageNumber.setText("لاپەڕە " + currentPage);
       // holder.lblSuraNAme.setText(suraName);
        if (arAyahForPage != null && arAyahForPage.size() > 0) {
            int juzzNumber = arAyahForPage.get(0).getJozzAsInt();
            holder.lblPageNumber.setText(holder.lblPageNumber.getText()+" | جوزء " + juzzNumber);
        }
        //atwanit esh bdait baw bashai sarawai parakash (heli awai tyay nwsrawa پەڕەی ).
        // Layout aw nwai loTopbar
        holder.loTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // agar atawet esh bdait baw bashai sarawa lera binwsa
            }
        });
        SharedPreferences sharedPreferencesSize = context.getSharedPreferences("size", MODE_PRIVATE);

        int quranTextSize = sharedPreferencesSize.getInt("quransize", 27);
        holder.lblAyahText.setMovementMethod(LinkMovementMethod.getInstance());
        holder.lblAyahText.setText(span);

        holder.lblAyahText.setTextSize(TypedValue.COMPLEX_UNIT_SP, quranTextSize);

        if (position==arPageNumbers.size()-1){
            holder.btnNextSura.setVisibility(View.VISIBLE);
            holder.iv_sadaqa.setVisibility(View.VISIBLE);
        }else {
            holder.btnNextSura.setVisibility(View.GONE);
            holder.iv_sadaqa.setVisibility(View.GONE);
        }

        if (suraId==114){
            holder.btnNextSura.setVisibility(View.GONE);
        }

        holder.btnNextSura.setOnClickListener(v -> {
            listener.onButtonClick(position);
        });


    }
    
    
    @Override
    public int getItemCount() {
        return arPageNumbers.size();
    }
    
    public static class QuranViewHolder extends RecyclerView.ViewHolder {
        TextView lblAyahText, lblPageNumber ,lblJuzNumber , lblSuraNAme , btnNextSura;
        // TextView quran_aya_number;
        // ImageButton btnCopy, btnSave;
        LinearLayout LinearLayout;
        LinearLayout loTopbar;

        ImageView iv_sadaqa;
        
        
        public QuranViewHolder(@NonNull View itemView) {
            super(itemView);
            
            lblAyahText = itemView.findViewById(R.id.lblAyahText);
            lblPageNumber = itemView.findViewById(R.id.lblPageNumber);
            btnNextSura = itemView.findViewById(R.id.btnNextSura);
            iv_sadaqa = itemView.findViewById(R.id.iv_sadaqa);

            LinearLayout = itemView.findViewById(R.id.ayaLayout);
            loTopbar = itemView.findViewById(R.id.loTopbar);
            
        }
    }
    public interface OnButtonClickListener{
        void onButtonClick(int position);
    }
}
