package com.example.rickyandmorty.ViewModel;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;

import com.google.android.material.imageview.ShapeableImageView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import okhttp3.HttpUrl;

public class CharacterDetailActivityViewModel extends ViewModel {
    public ImageView charImage;
    public ImageView charBackIcon;
    public TextView characterImage;
    public ImageView characterGender;


    public String parseEpisodes(List<String> url){
        String episodeIds = "";
        for(String Url : url){
            HttpUrl httpUrl = HttpUrl.parse(Url);
            String id = httpUrl.pathSegments().get(2);
            episodeIds += id + ", ";
        }
           return episodeIds.substring(0, episodeIds.length() - 2);
    }

    public String parseCreated(String date){
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
        return zonedDateTime1.format(dateTimeFormatter);
    }

}
