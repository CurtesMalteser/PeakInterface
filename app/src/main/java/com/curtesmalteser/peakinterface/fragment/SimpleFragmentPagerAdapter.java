package com.curtesmalteser.peakinterface.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.curtesmalteser.peakinterface.R;

/**
 * Created by António "Curtes Malteser" Bastião on 05/03/2018.
 */


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new YouFragment();
            case 1:
                return new AgeGroupFragment();
            case 2:
                return new ProfessionFragment();
            default:
                return new YouFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.string_you);
            case 1:
                return mContext.getString(R.string.string_age_group);
            case 2:
                return mContext.getString(R.string.string_profession);
            default:
                return null;
        }
    }
}
