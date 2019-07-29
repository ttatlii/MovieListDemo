package ttatli.com.movielistdemo.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ttatli.com.movielistdemo.R;
import ttatli.com.movielistdemo.adapter.MovieListAdapter;
import ttatli.com.movielistdemo.client.RetrofitClient;
import ttatli.com.movielistdemo.model.Movie;
import ttatli.com.movielistdemo.util.FavoriteMovieHelper;
import ttatli.com.movielistdemo.util.NotifyAction;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMoviesFragment extends Fragment {
    ListView listView;
    List<Movie> movieList;

    public FavoriteMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favorite_movies, container, false);
        listView=view.findViewById(R.id.favorite_movie_listview);
        movieList=new ArrayList<>();
        movieList=FavoriteMovieHelper.getMovieList(getContext());

        if(movieList==null)
            movieList=new ArrayList<>();

        final MovieListAdapter adapter = new MovieListAdapter(getContext(), movieList);
        listView.setAdapter(adapter);

        return view;
    }
    public void refresh(){

    }

}
