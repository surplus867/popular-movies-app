package com.example.android.popular_movies_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popular_movies_app.adapter.MoviesAdapter;
import com.example.android.popular_movies_app.model.Movie;
import com.example.android.popular_movies_app.model.MoviesResponse;
import com.example.android.popular_movies_app.retrofits.Client;
import com.example.android.popular_movies_app.retrofits.MovieApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "caa7bbb8acf08fcdc2b3f26cb3219b89";
    public static int PAGE = 1;
    private Call<MoviesResponse> call;
    private List<Movie> movieResults;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;



    public static final String TAG = MoviesAdapter.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Client Client = new Client();
        MovieApi movieApi = Client.getClient().create(MovieApi.class);
        Call<MoviesResponse> call = movieApi.getPopularMovies(PAGE, API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {



            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });

    }


}

}
