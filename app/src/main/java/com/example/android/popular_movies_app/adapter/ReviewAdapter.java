package com.example.android.popular_movies_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.popular_movies_app.R;
import com.example.android.popular_movies_app.model.Review;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Review[] mReviewData;
    public TextView mReviewListTextView;
    public TextView mAuthorListTextView;

    private Context mContext;

    public ReviewAdapter(Review[] review) {
        mReviewData = review;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        int layoutIdForListItem = R.layout.row_trailer;
        View view = layoutInflater.inflate(layoutIdForListItem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {
        String AuthorToBind = mReviewData[position].getAuthor();
        String ReviewToBind = mReviewData[position].getContent();
        mReviewListTextView.setText(ReviewToBind);
        mAuthorListTextView.setText(AuthorToBind + " said: ");
    }

    @Override
    public int getItemCount() {
        if (null == mReviewData) {
            return 0;
        }
        return mReviewData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mReviewListTextView;
        public TextView mAuthorListTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            mReviewListTextView =  itemView.findViewById(R.id.row_review);
            mAuthorListTextView =  itemView.findViewById(R.id.tv_author);
        }
    }
}