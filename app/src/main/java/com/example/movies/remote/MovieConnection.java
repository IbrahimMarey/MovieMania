package com.example.movies.remote;

import com.example.movies.helpers.youtube.YoutubeSearchResponse;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.views.movieDetails.view.IMovieDetailView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieConnection implements MovieConnectionInterface
{
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY="a4e12a16d1df010db28f0f570554e512";
    public static final String IMAGE_URL="https://image.tmdb.org/t/p/w500";
    public static final String YoutubeAPI_KEY = "AIzaSyCdAVi5z_C1WStIlghm-4GiaKJWpNguOAg";
    public static final String YoutubeBaseURL = "https://youtube.googleapis.com/youtube/v3/search?";
    private static MovieConnection movieConnection;
    private MovieServices movieServices;
    private MovieConnection()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieServices = retrofit.create(MovieServices.class);
    }

    public static synchronized MovieConnection getInstance()
    {
        if (movieConnection == null)
            movieConnection= new MovieConnection();
        return movieConnection;
    }

    @Override
    public Single<MovieListPojo> getTrending()
    {
        return movieServices.getTrending(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getUpComing() {
        return movieServices.getUpComing(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getPopular() {
        return movieServices.getPopular(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getTopRated() {
        return movieServices.getTopRated(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getDiscover() {
        return movieServices.getDiscover(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getSearch(String search) {
        return movieServices.getSearch(API_KEY,search);
    }

    public void getMovie(String title , IMovieDetailView iMovieDetailView) {
        try {
            String encodedQuery = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
            String url = YoutubeBaseURL + "q=" + encodedQuery + "&key=" + YoutubeAPI_KEY;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String responseBody = Objects.requireNonNull(response.body()).string();
                        YoutubeSearchResponse results = new Gson().fromJson(responseBody, YoutubeSearchResponse.class);
                        if (results != null && results.items != null && !results.items.isEmpty()) {
                            String videoId=results.items.get(0).id.videoId;
                            iMovieDetailView.onSuccess(videoId);
                        } else {
//                            iMovieDetailView.showToast("No results found");
                        }
                    } catch (Exception e) {
//                        iMovieDetailView.showToast(e.getMessage());
                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}