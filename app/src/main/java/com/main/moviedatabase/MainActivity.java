package com.main.moviedatabase;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.List;

/**
 * The main activity handling UI initialization and movie list loading.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;
    private TextView stickyHeaderTextView;
    public MaterialSwitch hideMatSwitch;
    private List<Movie> movies;
    private boolean hide = false;

    /**
     * Called when the activity is created. Initializes layout, sets up listeners, and loads movies.
     *
     * @param savedInstanceState the saved instance data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        stickyHeaderTextView = findViewById(R.id.stickyHeaderTextView);
        stickyHeaderTextView.setText("All Movies"); // Set initial text
        stickyHeaderTextView.setPadding(64, result + 32, 32, 32);

        hideMatSwitch = findViewById(R.id.hideSwitch);
        hideMatSwitch.setChecked(hide);
        hideMatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hide = isChecked;
                reloadMovies();
            }
        });

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(layoutManager);

        reloadMovies();
    }

    /**
     * Reloads the movie list from the JSON file. Displays errors if loading fails.
     */
    private void reloadMovies() {
        MovieLoading movieLoading = new MovieLoading(this);
        if (hide) {
            movieLoading.setHide(true);
        }
        movies = movieLoading.loadMovies();

        if (movies.isEmpty()) {
            Log.e(TAG, "No movies loaded. Check the JSON file and parsing.");
            // Show an error message to the user
            Toast.makeText(this, "Error loading movies", Toast.LENGTH_LONG).show();
        } else {
            movieAdapter = new MovieAdapter(this, movies);
            if (hide) {
                movieAdapter.setHide(true);
            }
            movieRecyclerView.setAdapter(movieAdapter);
        }
    }
}