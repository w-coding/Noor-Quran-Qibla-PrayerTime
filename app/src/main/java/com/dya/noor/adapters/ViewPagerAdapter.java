package com.dya.noor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dya.noor.R;
import com.dya.noor.database.MydbClass;
import com.dya.noor.utlis.QuranPageUtils;

import java.io.File;
import java.util.Locale;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private final Context context;
    private final String folderName;
    private final int imageCount;
    private SurahNameListener surahNameListener;

    public ViewPagerAdapter(Context context, String folderName ,SurahNameListener listener) {
        this.context = context;
        this.folderName = folderName;

        this.surahNameListener = listener;

        // Calculate the number of images based on the naming pattern
        this.imageCount = calculateImageCount(folderName);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        int currentPageNumber = position + QuranPageUtils.pageNumber;

        if (currentPageNumber > 604) {
            return;
        }

        // Format the number to ensure it's always three digits and english
        String formattedNumber = String.format(Locale.ENGLISH,"%03d", currentPageNumber);

        // Load the image
        String imageName = "page" + formattedNumber + ".png";
        File imageFile = new File(context.getExternalFilesDir(null), folderName + File.separator + imageName);

        Glide.with(context)
                .load(imageFile)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        // Set page number
        holder.txtPageNumber.setText(String.valueOf(currentPageNumber));

        // Fetch and notify Surah name
        MydbClass mydbClass = new MydbClass(context);
        String surahName = mydbClass.getSurahNameByPage(currentPageNumber);
        if (surahNameListener != null) {
            surahNameListener.onSurahNameChanged(surahName.isEmpty() ? "Unknown Surah" : surahName);
        }

      /** int currentPageNumber = position + QuranPageUtils.pageNumber;


        if (currentPageNumber > 604) {
            // Optional: Handle the case where the page number is invalid, such as showing a blank screen or an error
            return;
        }
// Format the number to ensure it's always three digits
        String formattedNumber = String.format("%03d", currentPageNumber);
        // Construct the URL with the formatted number

        String imageName = "page"+formattedNumber + ".png";
        String PageNumber = String.valueOf(currentPageNumber);

        // Get the image file path
        File imageFile = new File(context.getExternalFilesDir(null), folderName + File.separator + imageName);

        // Load the image using Glide as you already do
        Glide.with(context)
                .load(imageFile)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        // Set the image name in the TextView
        holder.txtPageNumber.setText(PageNumber);

        /


        // Construct the image file name based on the position
      //  String imageName =  (position + QuranPageUtils.pageNumber) + ".png"; // Adjust the starting number as needed
      //  String PageNumber =  "Page " +(position + QuranPageUtils.pageNumber); // Adjust the starting number as needed

        // Get the image file path
      //  File imageFile = new File(context.getExternalFilesDir(null), folderName + File.separator + imageName);

        // Load the image using Glide with error handling and caching
       /** Glide.with(context)
                .load(imageFile)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher_background) // Set a placeholder drawable
                .error(R.drawable.ic_launcher_foreground) // Set an error drawable
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstLoad) {
                        // Image loaded successfully, handle if necessary
                        return false;
                    }

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstLoad) {
                        // Handle image loading failure
                        Log.e("ViewPagerAdapter", "Failed to load image: " + imageFile.getAbsolutePath(), e);
                        return false;
                    }
                })
                .into(holder.imageView);*/
        // Set the image name in the TextView
       // holder.txtPageNumber.setText(PageNumber);

    }

    @Override
    public int getItemCount() {
        int totalPageCount = 604; // Total number of Quran pages
        int startingPage = QuranPageUtils.pageNumber; // Page to start from when user selects a Surah
        int remainingPages = totalPageCount - startingPage;

        // Ensure that the user cannot swipe past the last page
        return Math.min(imageCount, remainingPages + 1); // Ensure we don't go beyond page 604


       // return imageCount;
    }

    // Helper method to calculate the number of images based on the naming pattern
    public  int calculateImageCount(String folderName) {
        File folder = new File(context.getExternalFilesDir(null), folderName);
        if (!folder.exists()) {
            return 0; // No folder found
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return 0; // No files in folder
        }

        int imageCount = 0;
        for (File file : files) {
            if (file.isFile() && isValidImageFile(file)) {
                imageCount++;
            }
        }
        return imageCount;
    }
    // Helper method to check if a file is a valid image (optional)
    private boolean isValidImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        TextView txtPageNumber;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view); // Assuming ImageView ID in image_item_layout.xml
            txtPageNumber = itemView.findViewById(R.id.txtPageNumber); // Assuming txtPageNumber ID in image_item_layout.xml
        }
    }
    // Define the interface
    public interface SurahNameListener {
        void onSurahNameChanged(String surahName);
    }


}
