package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popular_movies_app.R;
import com.example.android.popular_movies_app.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private Context mContext;
    private List<Trailer> mTrailer;

    public TrailerAdapter(Context context, List<Trailer> trailer) {
        mContext = context;
        mTrailer = trailer;
    }


    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_trailer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Trailer trailer = mTrailer.get(position);
        holder.rowTrailer.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return mTrailer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_trailer)
        TextView rowTrailer;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Trailer trailer = mTrailer.get(pos);
                        String videoKey = mTrailer.get(pos).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoKey));
                        intent.putExtra("VIDEO_ID", videoKey);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + trailer.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}