package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popular_movies_app.R;
import com.example.android.popular_movies_app.model.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private Trailer[] mTrailerData;
    String TMDB_TRAILER_BASE_URL = "https://www.youtube.com/watch?v=";
    public static TextView mTrailerListTextView = null;
    private Context mContext;

    public TrailerAdapter(Trailer[] trailer, Context context) {
      mContext = context;
      mTrailerData = trailer;
    }

    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.row_trailer;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TrailerAdapter.ViewHolder holder, int position) {
        String TrailerToBind = mTrailerData[position].getName();
        final String TrailerToWatch = mTrailerData[position].getName();
        mTrailerListTextView.setText(TrailerToBind);
        holder.itemView.setOnClickListener(view -> {
            Uri openTrailerVideo = Uri.parse(TMDB_TRAILER_BASE_URL + TrailerToWatch);
            Intent intent = new Intent(Intent.ACTION_VIEW, openTrailerVideo);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
       if(null == mTrailerData) {
           return 0;
       }
       return mTrailerData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            mTrailerListTextView = itemView.findViewById(R.id.row_trailer);
        }
    }
}