package com.main.moviedatabase;

import android.content.Context;
import android.util.Log;

public class Movie {
    private static final String TAG = "Movie";
    private static final String DEFAULT_TITLE = "null";
    private static final int DEFAULT_YEAR = 0;
    private static final String DEFAULT_GENRE = "No Genre Available";

    private String title;
    private Integer year;
    private String genre;
    private String poster;

    public Movie(String title, Integer year, String genre, String poster) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.poster = poster;
        if (title == null || title.trim().isEmpty()) {
            Log.e(TAG, "Invalid movie: title cannot be null or blank");
        }
        if (year != null && year < 1888) { // first movie was created in 1888
            Log.e(TAG, "Invalid movie: year must be greater than 1888");
        }
        if (genre == null || genre.trim().isEmpty()) {
            Log.e(TAG, "Invalid movie: genre cannot be null or blank");
        }
    }

    public String getTitle() {
        return title == null || title.trim().isEmpty() ? DEFAULT_TITLE : title;
    }

    public Integer getYear() {
        return year == null || year < 1888 ? DEFAULT_YEAR : year;
    }

    public String getGenre() {
        return genre == null || genre.trim().isEmpty() ? DEFAULT_GENRE : genre;
    }

    public String getPoster() {
        return poster;
    }

    public int getPosterResourceId(Context context) {
        try {
            if (poster != null) {
                int resourceId = context.getResources().getIdentifier(poster, "drawable", context.getPackageName());
                if (resourceId != 0) {
                    return resourceId;
                } else {
                    Log.e(TAG, "Could not find resource: " + poster + " in drawable");
                }
            }
            return R.drawable.default_placeholder;
        } catch (Exception e) {
            Log.e(TAG, "Error loading poster resource for " + poster + ": " + e.getMessage());
            return R.drawable.default_placeholder;
        }
    }
}
