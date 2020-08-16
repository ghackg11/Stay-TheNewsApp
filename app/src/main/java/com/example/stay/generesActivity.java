package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class generesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generes);


        final TextView sports = (TextView)findViewById(R.id.textView5);
        final TextView business = (TextView)findViewById(R.id.textView7);
        final TextView classifieds = (TextView)findViewById(R.id.textView8);
        final TextView culture = (TextView)findViewById(R.id.textView9);
        final TextView edu = (TextView)findViewById(R.id.textView10);
        final TextView enterpreneur = (TextView)findViewById(R.id.textView11);
        final TextView health = (TextView)findViewById(R.id.textView12);
        final TextView movies = (TextView)findViewById(R.id.textView13);



        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=sports+india");
                intent.putExtra("header",sports.getText().toString());
                startActivity(intent);
            }
        });

        classifieds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&fq=news_desk(\"Classifieds\") AND glocations:(\"INDIA\")");
                intent.putExtra("header",classifieds.getText().toString());
                startActivity(intent);

            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=business");
                intent.putExtra("header",business.getText().toString());
                startActivity(intent);
            }
        });

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=indian+culture");
                intent.putExtra("header",culture.getText().toString());
                startActivity(intent);
            }
        });

        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=education+india");
                intent.putExtra("header",edu.getText().toString());
                startActivity(intent);
            }
        });

        enterpreneur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=entrepreneurs");
                intent.putExtra("header",enterpreneur.getText().toString());
                startActivity(intent);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=health+fitness");
                intent.putExtra("header",health.getText().toString());
                startActivity(intent);
            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(generesActivity.this,NewsShowingActivity.class);
                intent.putExtra("url","https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q=Bollywood+india+movies");
                intent.putExtra("header",movies.getText().toString());
                startActivity(intent);
            }
        });
    }
}
