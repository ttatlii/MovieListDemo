package ttatli.com.movielistdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ttatli.com.movielistdemo.adapter.CustomPagerAdapter;
import ttatli.com.movielistdemo.ui.FavoriteMoviesFragment;
import ttatli.com.movielistdemo.ui.TopRatedFragment;
import ttatli.com.movielistdemo.ui.UpComingFragment;
import ttatli.com.movielistdemo.util.FavoriteMovieHelper;
import ttatli.com.movielistdemo.util.NotifyAction;

public class MainActivity extends AppCompatActivity implements NotifyAction{
    ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getApplicationContext(),getSupportFragmentManager());
        adapter.addFragment(new UpComingFragment(this));
        adapter.addFragment(new TopRatedFragment(this));
        FavoriteMoviesFragment favoriteMoviesFragment=new FavoriteMoviesFragment();
        adapter.addFragment(favoriteMoviesFragment);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onNotified(Object key, Object object) {

    }
}

