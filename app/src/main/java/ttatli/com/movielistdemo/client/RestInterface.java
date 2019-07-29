package ttatli.com.movielistdemo.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ttatli.com.movielistdemo.model.MovieResult;

public interface RestInterface {
    @GET("discover/movie")
    Call<MovieResult> getMoviePopularMovie(@Query("api_key")String api_key,@Query("sort_by")String sort_by,@Query("page") Integer page);

    @GET("movie/upcoming")
    Call<MovieResult> getMovieUpComingMovie(@Query("api_key")String api_key,@Query("page") Integer page);

    @GET("movie/upcoming")
    Call<MovieResult> getMovieFavoriteMovie(@Query("api_key")String api_key,@Query("page") Integer page);

    @GET("search/movie")
    Call<MovieResult> getSearchMovie(@Query("api_key")String api_key,@Query("query") String search);

}
