package com.main.moviedatabase;

import android.content.Context;
import android.util.Log;

/**
 * Represents a movie entity with title, year, genre, and poster details.
 * Provides default values and basic validation for certain attributes.
 */
public class Movie {
    private static final String TAG = "Movie";
    private static final String DEFAULT_TITLE = "null";
    private static final int DEFAULT_YEAR = 0;
    private static final String DEFAULT_GENRE = "No Genre Available";

    private String title;
    private Integer year;
    private String genre;
    private String poster;

    /**
     * Constructs a Movie object with the specified attributes.
     *
     * @param title  the movie title
     * @param year   the movie release year
     * @param genre  the movie genre
     * @param poster the drawable resource name for the poster
     */
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

    /**
     * Returns the movie title or a default if invalid.
     *
     * @return the valid movie title
     */
    public String getTitle() {
        return title == null || title.trim().isEmpty() ? DEFAULT_TITLE : title;
    }

    /**
     * Returns the movie year or 0 if invalid.
     *
     * @return the valid movie year
     */
    public Integer getYear() {
        return year == null || year < 1888 ? DEFAULT_YEAR : year;
    }

    /**
     * Returns the movie genre or a default if invalid.
     *
     * @return the valid movie genre
     */
    public String getGenre() {
        return genre == null || genre.trim().isEmpty() ? DEFAULT_GENRE : genre;
    }

    /**
     * Returns the raw poster resource name.
     *
     * @return the poster resource name
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Retrieves the corresponding drawable resource ID or a placeholder.
     *
     * @param context the context for resource resolution
     * @return the drawable resource ID
     */
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
