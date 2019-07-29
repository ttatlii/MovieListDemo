package ttatli.com.movielistdemo.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class UpComingFragment extends Fragment {
ListView listView;
NotifyAction notifyAction;
EditText search;

    List<Movie> movieList;

    public UpComingFragment() {
    }

    public UpComingFragment(NotifyAction notifyAction) {
        // Required empty public constructor
        this.notifyAction=notifyAction;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_up_coming, container, false);
        listView=view.findViewById(R.id.up_coming_movie_listview);
        search= view.findViewById(R.id.edt_search);
        movieList = new ArrayList<>();
        final MovieListAdapter adapter = new MovieListAdapter(getContext(), movieList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                alertDialogBuilder.setTitle("Select Favorite Movie");

                // alert dialog özelliklerini oluşturuyoruz.
                alertDialogBuilder
                        .setMessage("Do you select favorite movie?")
                        .setCancelable(false)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FavoriteMovieHelper.saveMove(movieList.get(position),getContext());
                            }
                        })
                        .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=3){
                    RetrofitClient.getSearchMovieList(1, s.toString(), new NotifyAction() {
                        @Override
                        public void onNotified(Object key, Object object) {
                            List<Movie> result = (List<Movie>) object;
                            movieList.clear();
                            for (Movie movie : result) {
                                movieList.add(movie);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RetrofitClient.getUpcomingMovieList(1, new NotifyAction() {
            @Override
            public void onNotified(Object key, Object object) {
                List<Movie> result = (List<Movie>) object;
                for (Movie movie : result) {
                    movieList.add(movie);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

}
