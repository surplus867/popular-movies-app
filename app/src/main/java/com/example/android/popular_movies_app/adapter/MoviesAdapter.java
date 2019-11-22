package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

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
        final Movie currentMovie = mMovies.get(position);
        if (convertView == null) {
            view = mInflater.inflate(R.layout.movie_list_item, parent, false);
            holder = new ViewHolder();
            holder.posterImage = (ImageView) view.findViewById(R.id.iv_movie_posters);
            view.setTag(holder);

        }else{
            view = convertView;
            holder = (ViewHolder) convertView.getTag();

        }

    }
}
