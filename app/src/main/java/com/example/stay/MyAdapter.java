package com.example.stay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

class MyAdapter extends ArrayAdapter<NewsItem> {

    public Context mcontext;
    public ArrayList<NewsItem> newslist;

    public MyAdapter(Context context, int resource, ArrayList<NewsItem> newsList) {
        super(context, 0, newsList);

        mcontext = context;
        this.newslist = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NewsItem current = getItem(position);
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.news_layout,parent,false);
        }
        TextView heading = (TextView)convertView.findViewById(R.id.headline);
        final TextView description = (TextView)convertView.findViewById(R.id.content);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);

        heading.setText(current.getHeading());
        description.setText(current.getDescription());
        Transformation transformation = new RoundedCornersTransformation(30,0);
        Picasso.get().load(current.getImageUrl()).fit().transform(transformation).error(R.drawable.img1).into(imageView);

        heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.VISIBLE);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }
}
