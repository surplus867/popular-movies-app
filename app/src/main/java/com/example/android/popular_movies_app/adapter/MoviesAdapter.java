package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.android.popular_movies_app.R;
import com.example.android.popular_movies_app.model.Movie;

import java.util.List;

public class MoviesAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Movie> mMovies;
    private Context mContext;

    public MoviesAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMovies = movies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        final ViewHolder holder;
        final Movie movie = mMovies.get(position);
        if(convertView == null) {
            view = mInflater.inflate(R.layout.row_movie, parent, false);
            holder = new ViewHolder();
            holder.rowImage = (ImageView) view.findViewById(R.id.row_image);
            holder.rowLayout = (RelativeLayout) view.findViewById(R.id.row_layout);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load("http://image.tmdb.org/t/p/w185"+movie.getMoviePoster()).into(holder.rowImage);

        return view;

    }

    public static class ViewHolder {

        public RelativeLayout rowLayout;

        public ImageView rowImage;

    }
}
