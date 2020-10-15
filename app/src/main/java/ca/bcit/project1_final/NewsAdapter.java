package ca.bcit.project1_final;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    Context _context;
    public NewsAdapter(Context context, ArrayList<News> newss) {
        super(context, 0, newss);
        _context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        News news = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        // Lookup view for data population
        TextView tvFullName = convertView.findViewById(R.id.author);
        TextView tvOccupation = convertView.findViewById(R.id.title);

        // Populate the data into the template view using the data object
        tvFullName.setText(news.getTitle());
        tvOccupation.setText(news.getTitle());

//        ImageView imgOnePhoto = convertView.findViewById(R.id.thumbImage);
//        if (news.getUrlToImage() != null) {
//            new ImageDownloaderTask(imgOnePhoto).execute(news.getUrlToImage());
//        }

        // Return the completed view to render on screen
        return convertView;
    }
}

