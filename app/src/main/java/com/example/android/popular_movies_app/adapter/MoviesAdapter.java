package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.DetailActivity;
import com.example.android.popular_movies_app.R;
import com.example.android.popular_movies_app.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    private List<Movie> mMovies;
    private Context mContext;

    public static final String TAG = MoviesAdapter.class.getSimpleName();

    public MoviesAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;

    }

    public void updateData(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_movie, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {

        final Movie movie = mMovies.get(position);
        holder.rowMovie.setText(movie.getTitle());
        Glide.with(mContext).load(IMAGE_BASE_URL+ movie.getMoviePoster()).into(holder.rowImage);

        holder.rowLayout.setOnClickListener(v -> {
            Toast.makeText(mContext, "Clicked on" + movie.getTitle(),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(DetailActivity.ARG_Movie, movie);
            mContext.startActivity(intent);

        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_layout)
        RelativeLayout rowLayout;

        @BindView(R.id.row_image)
        ImageView rowImage;

        @BindView(R.id.row_movie)
        TextView rowMovie;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}