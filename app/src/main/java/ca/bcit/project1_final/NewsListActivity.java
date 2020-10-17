package ca.bcit.project1_final;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static ca.bcit.project1_final.News.newsDetails;

public class NewsListActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    private ArrayList<HashMap<String, String>> news = new ArrayList<>();
    private String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistactivity_layout);

        new GetContacts().execute();
        Intent intent = getIntent();
        userInput = intent.getStringExtra(MainActivity.KEYWORD);

        lv = findViewById(R.id.newsList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewsListActivity.this, NewsDetailsActivity.class);
                intent.putExtra("index", (int) l);
                startActivity(intent);
            }
        });
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String API_KEY = "dca6881472de4de5a8752112adabcd9b";

            // get current date in 1111-11-11 format
            // then -1 week
            // assignment documents mentions:  starting date which should always be one week behind
            LocalDate currentDate = LocalDate.now();
            LocalDate weekFromNow = currentDate.minus(1, ChronoUnit.WEEKS);
//            System.out.println(weekFromNow);

            String SERVICE_URL = "https://newsapi.org/v2/everything?q=" + userInput
                    + "&from=" + weekFromNow.toString() + "&sortBy=publishedAt&apiKey=" + API_KEY;
//            System.out.println(SERVICE_URL);


            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray articles = jsonObj.getJSONArray("articles");
//                    System.out.println(articles);

                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c = articles.getJSONObject(i);
                        String author = c.getString("author");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String url = c.getString("url");
                        String urlToImage = c.getString("urlToImage");
                        String publishedAt = c.getString("publishedAt");
                        String content = c.getString("content");

                        HashMap<String, String> mainMenu = new HashMap<>();

                        mainMenu.put("author", author);
                        mainMenu.put("title", title);
                        newsDetails.add(new News(author, title, description,
                                url, urlToImage,
                                publishedAt, content));
                        news.add(mainMenu);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(NewsListActivity.this, news,
                    R.layout.list_row, new String[]{ "author","title"},
                    new int[]{R.id.title, R.id.author});
            lv.setAdapter(adapter);
        }


    }

}