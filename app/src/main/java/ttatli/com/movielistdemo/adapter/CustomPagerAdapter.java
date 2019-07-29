package ttatli.com.movielistdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ttatli.com.movielistdemo.R;

public class CustomPagerAdapter  extends FragmentPagerAdapter {
    List<Fragment> mFragmenList;
    private Context mContext;

    public CustomPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mFragmenList=new ArrayList<>();
        mContext=context;
    }

    public void addFragment(Fragment fragment){
        mFragmenList.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmenList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmenList.size();
    }
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.upcoming_movie);
            case 1:
                return mContext.getString(R.string.top_movie);
            case 2:
                return mContext.getString(R.string.favorite_movie);

            default:
                return null;
        }
    }
}
