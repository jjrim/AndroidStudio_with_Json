package ca.bcit.project1_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static ca.bcit.project1_final.News.newsDetails;

public class MainActivity extends AppCompatActivity {

    public static String KEYWORD;
    Button searchBtn;
    EditText searchByKeyword;
    String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void setOnClickListener(View v) {
        newsDetails.clear();
        searchBtn = findViewById(R.id.searchBtn);
        searchByKeyword = findViewById(R.id.searchByKeywordInput);
        Intent i = new Intent(this, NewsListActivity.class);
        keyword = searchByKeyword.getText().toString();
        i.putExtra(KEYWORD, keyword);
        startActivity(i);
    }
}