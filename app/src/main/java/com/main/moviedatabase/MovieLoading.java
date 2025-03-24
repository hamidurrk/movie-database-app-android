package com.main.moviedatabase;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieLoading {
    private static final String TAG = "MovieLoading";
    private Context context;
    private boolean hide = false;

    public MovieLoading(Context context) {
        this.context = context;
    }

    public List<Movie> loadMovies() {
        String fileName = "movies.json";
        String jsonString;
        try {
            jsonString = getJsonDataFromAsset(fileName);
        } catch (IOException e) {
            Log.e(TAG, "Error reading file " + fileName + ": " + e.getMessage());
            return new ArrayList<>();
        }

        if (jsonString == null) {
            Log.e(TAG, "The file " + fileName + " is empty or unreadable.");
            return new ArrayList<>();
        }

        try {
            return parseMovies(jsonString);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private String getJsonDataFromAsset(String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    private List<Movie> parseMovies(String jsonString) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.length() == 0 && hide) {
            Log.d(TAG, "parseMovies: " + jsonObject.length());
                continue;
            }
            String title = jsonObject.optString("title", null);
            Object yearObject = jsonObject.opt("year"); // Get as Object first
            int year;
            if (yearObject instanceof Integer) {
                year = (Integer) yearObject;
            } else {
                year = 0;
            }
            String genre = jsonObject.optString("genre", null);
            String poster = jsonObject.optString("poster", null);
            Movie movie = new Movie(title, year, genre, poster);
            movies.add(movie);
        }
        return movies;
    }
}