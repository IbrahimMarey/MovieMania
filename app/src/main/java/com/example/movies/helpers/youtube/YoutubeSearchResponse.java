package com.example.movies.helpers.youtube;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeSearchResponse {
    @SerializedName("items")
    public List<VideoElement> items;
}

