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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private boolean hide = false;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        return movieList.size();
    }

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