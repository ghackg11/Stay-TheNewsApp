package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public EditText editText;
    public String []keywords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView searchbtn = (ImageView) findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText = (EditText)findViewById(R.id.editText);
                keywords = editText.getText().toString().trim().split(" ");

                if(!editText.getText().toString().equals("")) {
                    String finalkey = "";
                    for (int i = 0; i < keywords.length; i++) {
                        if (i == keywords.length - 1) {
                            finalkey += keywords[i];
                            break;
                        }
                        finalkey += keywords[i] + "+";
                    }
                    String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=eOlhGDZ8cWenLXD5rUg6wtrHJIttrUDL&q="+finalkey;
                    Intent intent = new Intent(MainActivity.this, NewsShowingActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("header",editText.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });

        Button exploreButton = (Button)findViewById(R.id.explorebtn);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgeneres = new Intent(MainActivity.this,generesActivity.class);
                startActivity(intentgeneres);
            }
        });
    }
}
