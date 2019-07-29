package ttatli.com.movielistdemo.client;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ttatli.com.movielistdemo.model.MovieResult;
import ttatli.com.movielistdemo.util.Const;
import ttatli.com.movielistdemo.util.Enums;
import ttatli.com.movielistdemo.util.NotifyAction;

public class RetrofitClient {
    private static Retrofit retrofit=null;

    private static String Base_Url= "https://api.themoviedb.org/3/";
    public static Retrofit getClient(){
        if(retrofit== null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
    public static void getPopularMovieList(int page,String sortBy, final NotifyAction action){

        RestInterface restInterface=getClient().create(RestInterface.class);
        Call<MovieResult> call= restInterface.getMoviePopularMovie(Const.apiKey,sortBy,page);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                action.onNotified(Enums.GetPopular,response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
    }
    public static void getUpcomingMovieList(int page,final NotifyAction action){
      RestInterface  restInterface=getClient().create(RestInterface.class);
        Call<MovieResult> call= restInterface.getMovieUpComingMovie(Const.apiKey,page);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                action.onNotified(Enums.GetUpcomming,response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
    }
    public static  void getSearchMovieList(int page, String query, final NotifyAction action){
        RestInterface restInterface =getClient().create(RestInterface.class);
        Call<MovieResult> call=restInterface.getSearchMovie(Const.apiKey,query);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                action.onNotified(Enums.GetSearch,response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
    }
}
