package ca.bcit.project1_final;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        int articleIndex = (Integer) getIntent().getExtras().get("index");
        News news = News.newsDetails.get(articleIndex);

        ImageView imgOnePhoto = findViewById(R.id.photo);
        if (news.getUrlToImage() != null) {
            new ImageDownloaderTask(imgOnePhoto).execute(news.getUrlToImage());
        }

//        ImageView photo = findViewById(R.id.photo);
//        photo.setImageResource(news.getUrlToImage());
//
//                ImageView imgOnePhoto = convertView.findViewById(R.id.thumbImage);
//        if (news.getUrlToImage() != null) {
//            new ImageDownloaderTask(imgOnePhoto).execute(news.getUrlToImage());
//        }

        TextView author = findViewById(R.id.author);
        author.setText(news.getAuthor());

        TextView title = findViewById(R.id.title);
        title.setText(news.getTitle());

        TextView publishedAt = findViewById(R.id.publishedAt);
        publishedAt.setText(news.getPublishedAt());

        TextView content = findViewById(R.id.content);
        content.setText(news.getContent());




    }
}