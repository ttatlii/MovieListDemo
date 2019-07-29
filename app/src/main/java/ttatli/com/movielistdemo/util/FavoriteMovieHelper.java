package ttatli.com.movielistdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ttatli.com.movielistdemo.model.Movie;

public class FavoriteMovieHelper {

    public static void saveMove(Movie movie, Context context){
        List<Movie> currentList=getMovieList(context);
        if(currentList==null)
            currentList=new ArrayList<>();
        currentList.add(movie);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currentList);
        editor.putString(Const.SharedMovieKey, json);
        editor.apply();
    }

    public static ArrayList<Movie> getMovieList(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(Const.SharedMovieKey, null);
        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
