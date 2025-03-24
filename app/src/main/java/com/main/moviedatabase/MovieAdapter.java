package com.main.moviedatabase;

import static com.main.moviedatabase.R.color.red;
import static com.main.moviedatabase.R.color.white;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Binds a list of Movie objects to RecyclerView item views.
 * Handles dynamic visibility based on user preferences.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private boolean hide = false;

    /**
     * Constructs a MovieAdapter with the provided data and context.
     *
     * @param context   the context for resource access
     * @param movieList the initial list of Movie objects to display
     */
    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    /**
     * Sets whether to hide incomplete data fields in the item views.
     *
     * @param hide true to hide fields; false otherwise
     */
    public void setHide(boolean hide) {
        this.hide = hide;
    }

    /**
     * Inflates the item layout and creates a new ViewHolder.
     *
     * @param parent   the parent view group
     * @param viewType the view type for the item
     * @return a new MovieViewHolder for the item
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    /**
     * Binds the Movie object data to the given ViewHolder.
     *
     * @param holder   the holder to update
     * @param position the item's position in the list
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        if (movie.getTitle().equals("null")) {
            holder.titleTextView.setText("No Title Available");
            holder.titleTextView.setTextColor(context.getResources().getColor(red,context.getTheme()));
            if (hide) {
                holder.titleTextView.setVisibility(View.GONE);
            } else {
                holder.titleTextView.setVisibility(View.VISIBLE);
            }
        } else {
            holder.titleTextView.setText(movie.getTitle());
            holder.titleTextView.setTextColor(context.getResources().getColor(white,context.getTheme()));
        }
        if (movie.getYear() == 0) {
            holder.yearTextView.setText("No Year Available");
            holder.yearTextView.setTextColor(context.getResources().getColor(red,context.getTheme()));
            if (hide) {
                holder.yearTextView.setVisibility(View.GONE);
            } else {
                holder.yearTextView.setVisibility(View.VISIBLE);
            }
        } else {
            holder.yearTextView.setText(String.valueOf(movie.getYear()));
            holder.yearTextView.setTextColor(context.getResources().getColor(white,context.getTheme()));
        }
        String genre = movie.getGenre();
        if (genre.equals("No Genre Available")) {
            holder.genreTextView.setText(genre);
            holder.genreTextView.setTextColor(context.getResources().getColor(red,context.getTheme()));
            if (hide) {
                holder.genreTextView.setVisibility(View.GONE);
            } else {
                holder.genreTextView.setVisibility(View.VISIBLE);
            }
        } else {
            holder.genreTextView.setText(genre);
            holder.genreTextView.setTextColor(context.getResources().getColor(white,context.getTheme()));
        }
        holder.posterImageView.setImageResource(movie.getPosterResourceId(context));
    }

    /**
     * Returns the total number of Movie items.
     *
     * @return the item count
     */
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /**
     * ViewHolder class holding references to item layout views.
     */
    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView, yearTextView, genreTextView;

        MovieViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.moviePosterImageView);
            titleTextView = itemView.findViewById(R.id.movieTitleTextView);
            yearTextView = itemView.findViewById(R.id.movieYearTextView);
            genreTextView = itemView.findViewById(R.id.movieGenreTextView);
        }
    }
}