package ttatli.com.movielistdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ttatli.com.movielistdemo.R;
import ttatli.com.movielistdemo.model.Movie;

public class MovieListAdapter extends ArrayAdapter<Movie> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final List<Movie> movieArrayList;
    public MovieListAdapter( Context context, List<Movie> objects) {
        super(context,0, objects);
        this.context = context;
        this.movieArrayList = objects;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Movie getItem(int position) {
        return movieArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieArrayList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_movie, null);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.overwiew=(TextView) convertView.findViewById(R.id.overview);
            holder.vote=(TextView) convertView.findViewById(R.id.vote);
            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        Movie movie = movieArrayList.get(position);
        if(movie != null){
            Picasso.get().load("https://image.tmdb.org/t/p/w370_and_h556_bestv2"+movie.getPoster_path()).into( holder.image);
            holder.title.setText(movie.getTitle());
            holder.date.setText(movie.getRelease_date());
            holder.overwiew.setText(movie.getOverview());
            holder.vote.setText(String.valueOf(movie.getVote_average()));


        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
        TextView date;
        TextView overwiew;
        TextView vote;

    }
}
