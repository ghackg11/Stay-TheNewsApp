package com.example.stay;

public class NewsItem {

    String imageUrl;
    String heading;
    String description;

    public NewsItem(String imageUrl,String heading,String description)
    {
        this.description = description;
        this.heading = heading;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }
}
