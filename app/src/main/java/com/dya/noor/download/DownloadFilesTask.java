package com.dya.noor.download;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dya.noor.R;
import com.dya.noor.utlis.QuranPageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadFilesTask extends AsyncTask<Void, Integer, Void> {
    private List<String> urls;
   public Context context;
   // public static String urlsText;

    private Dialog customDialog;
    private SeekBar seekBar;
    private TextView tvProgress;
    private TextView txtSize;

    public DownloadFilesTask(Context context, List<String> urls) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Initialize progress dialog or other UI elements here if needed

        customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.download_msg);
        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.setCancelable(false);

        // Initialize SeekBar and TextView from the custom layout
        seekBar = customDialog.findViewById(R.id.seekBarDown);
        tvProgress = customDialog.findViewById(R.id.tvDown);
        txtSize = customDialog.findViewById(R.id.txtSize);

        // Set SeekBar max value
        seekBar.setMax(urls.size());

        // Show the custom dialog
        customDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < urls.size(); i++) {
            downloadFile(urls.get(i));
            publishProgress(i + 1, urls.size()); // Update progress
        }
        return null;
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Update the UI with the current progress
        int currentFile = values[0];
        int totalFiles = values[1];

        // Update SeekBar and TextView
        seekBar.setProgress(currentFile);
        tvProgress.setText(currentFile + "/" + totalFiles);
       // Toast.makeText(context, currentFile + "/" + totalFiles, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Handle completion here (e.g., show a final message)

        // Dismiss the custom dialog and show completion message
        if (customDialog.isShowing()) {
            customDialog.dismiss();
        }

            Toast.makeText(context, "بەسەرکەوتی فایلەکان دابەزێنرا!", Toast.LENGTH_SHORT).show();
        }
    public void downloadFile(String fileUrl) {


        try {

            String folderName = QuranPageUtils.folderName;
            //  urlsText = fileUrl;
            // Create a File object for the folder
            File folder = new File(context.getExternalFilesDir(null), folderName);

            // Create the folder if it doesn't exist
            if (!folder.exists()) {
                folder.mkdirs();
            }
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            File file = new File(folder, fileUrl.substring(fileUrl.lastIndexOf('/') + 1));
            FileOutputStream fos = new FileOutputStream(file);

            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[8192]; // 8 KB buffer size
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            fos.close();
            is.close();
            connection.disconnect();

            // Get and display file size
            long fileSize = file.length();
            long fileSizeInKB = fileSize / 1024;
            long fileSizeInMB = fileSizeInKB / 1024;
            String fileSizeText;
            if (fileSizeInMB > 0) {
                fileSizeText = fileSizeInMB + " MB";
            } else {
                fileSizeText = fileSizeInKB + " KB";
            }
            txtSize.setText("" + fileSizeText);
            if (context != null) {
                // Initialize custom dialog and UI elements
                // ... existing code ...
            } else {
                // Handle the case where context is null (unlikely but possible)
                Log.e("DownloadFilesTask", "Context is null!");
                Toast.makeText(context, "Context is null! ", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }




