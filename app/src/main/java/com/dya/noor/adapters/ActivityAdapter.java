package com.dya.noor.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dya.noor.R;
import com.dya.noor.module.ActivityItem;
import com.dya.noor.ui.ActivityHajUmrah;
import com.dya.noor.ui.ActivityPrayersTime;
import com.dya.noor.ui.ActivityQuranPdf;
import com.dya.noor.ui.Babatyrozh;
import com.dya.noor.ui.CitiesSetting;
import com.dya.noor.ui.FarmwdaView;
import com.dya.noor.ui.Kteb;
import com.dya.noor.ui.MainActivity;
import com.dya.noor.ui.NameP;
import com.dya.noor.ui.Nawakany_xuda;
import com.dya.noor.ui.PayaKan;
import com.dya.noor.ui.Quran;
import com.dya.noor.ui.Salah;
import com.dya.noor.ui.Tasbih;
import com.dya.noor.ui.YoutubeChuser;
import com.dya.noor.ui.Zakat;
import com.dya.noor.ui.Zhian_Nama;
import com.dya.noor.ui.Zikr_Activity;
import com.hassanjamil.hqibla.CompassActivity;
import com.hassanjamil.hqibla.Constants;

import java.io.File;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>{

    Context context;
    List<ActivityItem> aName;

    public ActivityAdapter(Context con , List<ActivityItem> iName){
        context = con;
        aName = iName;
    }




    @NonNull
    @Override
    public ActivityAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.my_activity_row,parent,false);
        return new ActivityAdapter.ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.ActivityViewHolder holder, int position) {

        ActivityItem itemData =aName.get(position);
       // String itemPosition = itemData.getAyah();
        // check krdny internet
        ConnectivityManager connectivityManager  = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        //////////
        holder.ivImageResource.setImageResource(itemData.getImageResource());
        holder.tvDescription.setText(itemData.getDescription());

        String activityName = itemData.getName();
        MainActivity mainActivity = (MainActivity) context;
        holder.activityLayout.setOnClickListener(v -> {
            Intent intent;
            switch (activityName){
                case "Quran":
                    intent = new Intent(context, Quran.class);
                    // Start the activity.
                    context.startActivity(intent);
                    break;
                case "FarmwdaView":
                    intent = new Intent(context, FarmwdaView.class);
                    // Start the activity.
                    context.startActivity(intent);
                    break;


                    case "ActivityQuranPdf":
                    // agar filr habww ba bykatewa
                    File file2 = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"Quran.pdf");
                    if (file2.exists()) {
                         intent = new Intent(context, ActivityQuranPdf.class);
                         context.startActivity(intent);
                    }
                    // agar nabw ba download bkat
                    else {
                        // gar wifi yan mobile data habw ba download dastpebkat
                        if (wifi.isConnected()|| mobileNetwork.isConnected()) {

                           mainActivity.downloadQuranFiles();

                        }
                        // agar wifi yan mobile data nabww massagek pishan bat
                        else {
                            mainActivity.AgadariInternet();
                        }
                    }

                        break;

                case "Zhian_Nama":
                    intent = new Intent(context, Zhian_Nama.class);
                    context.startActivity(intent);
                    break;

                case "ActivityHajUmrah":
                    intent = new Intent(context, ActivityHajUmrah.class);
                    context.startActivity(intent);


                    break;

                case "Tasbih":
                    intent = new Intent(context, Tasbih.class);
                    context.startActivity(intent);

                    break;

                case "PayaKan":
                    intent = new Intent(context, PayaKan.class);
                    context. startActivity(intent);

                    break;
                case "Zikr_Activity":
                    intent = new Intent(context, Zikr_Activity.class);
                    context.startActivity(intent);

                    break;
                case "Zakat":
                    intent = new Intent(context, Zakat.class);
                    context.startActivity(intent);

                    break;
                case "Nawakany_xuda":
                    intent = new Intent(context, Nawakany_xuda.class);
                    context.startActivity(intent);

                    break;
                case "Salah":
                    intent = new Intent(context, Salah.class);
                    context.startActivity(intent);

                    break;
                case "NameP":
                    intent = new Intent(context, NameP.class);
                    context.startActivity(intent);

                    break;
                case"CompassActivity":
                     intent = new Intent(context, CompassActivity.class);
                    intent.putExtra(Constants.TOOLBAR_BG_COLOR,"#753E51");
                    intent.putExtra(Constants.TOOLBAR_TITLE,"قـیبلە نمـا");
                    // intent.putExtra(Constants.TOOLBAR_TITLE_COLOR,"#003B46");
                    intent.putExtra(Constants.COMPASS_BG_COLOR,"#753E51");
                    intent.putExtra(Constants.DRAWABLE_QIBLA,R.drawable.ic_qibla);
                    context.startActivity(intent);

                    break;
                case "Kteb":
                    intent = new Intent(context, Kteb.class);
                    context.startActivity(intent);
                    break;
                case "ActivityPrayersTime":
                    SharedPreferences sharedPreferences =context.getSharedPreferences("key",MODE_PRIVATE);
                   String City = sharedPreferences.getString("City","Kalar");

                    if (City.equals("")) {
                        intent = new Intent(context, CitiesSetting.class);
                    }else {
                        intent = new Intent(context, ActivityPrayersTime.class);
                    }
                    context.startActivity(intent);
                    ((MainActivity) context).finish();


                    break;
                case "YoutubeChuser":
                     intent = new Intent(context, YoutubeChuser.class);
                    context.startActivity(intent);

                    break;
                case "Babatyrozh":
                    intent = new Intent(context, Babatyrozh.class);
                    context.startActivity(intent);

                    break;

            }
        });


    }

    @Override
    public int getItemCount() {
        return aName.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImageResource;
        TextView tvDescription;
        LinearLayout activityLayout;
        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImageResource= itemView.findViewById(R.id.ivImageResource);
            tvDescription= itemView.findViewById(R.id.tvDescription);
            activityLayout= itemView.findViewById(R.id.activityLayout);
        }
    }
}
