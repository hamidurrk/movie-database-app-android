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

/**
 * Loads and parses movie data from a JSON asset file.
 * Offers an option to hide movies with incomplete data.
 */
public class MovieLoading {
    private static final String TAG = "MovieLoading";
    private Context context;
    private boolean hide = false;

    /**
     * Constructs a MovieLoading instance for JSON file operations.
     *
     * @param context the context for accessing app assets
     */
    public MovieLoading(Context context) {
        this.context = context;
    }

    /**
     * Loads movies from the asset file and returns a list of Movie objects.
     * Returns an empty list if file reading errors occur or parsing fails.
     *
     * @return a list of parsed Movie objects
     */
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

    /**
     * Sets whether incomplete movie entries should be hidden.
     *
     * @param hide true to hide incomplete entries; false otherwise
     */
    public void setHide(boolean hide) {
        this.hide = hide;
    }

    /**
     * Parses the given JSON string and converts it to a list of Movie objects.
     *
     * @param jsonString the JSON content to parse
     * @return a list of Movie objects constructed from JSON
     * @throws org.json.JSONException if the JSON is malformed
     */
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