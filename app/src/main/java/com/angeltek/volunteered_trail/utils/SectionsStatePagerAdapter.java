package com.angeltek.volunteered_trail.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SectionsStatePagerAdapt";

    private final List<Fragment> mFragmentList =new ArrayList<>();
    private  final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String, Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames = new HashMap<>();
    private int mCurrentPosition = -1;

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName) {
        mFragmentList.add(fragment);
        mFragments.put(fragment, mFragmentList.size() - 1);
        mFragmentNumbers.put(fragmentName, mFragmentList.size() - 1);
        mFragmentNames.put(mFragmentList.size(), fragmentName);

    }


    /**
     * for dynamic measuring of height on tab change
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment  = (Fragment) object;
            WrapContentViewPager pager = (WrapContentViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }

    /**
     * returns fragment with name @param
     * @param fragmentName
     * @return
     */
    public Integer getFragmentNumber(String fragmentName) {
        if(mFragmentNumbers.containsKey(fragmentName)) {
            return mFragmentNumbers.get(fragmentName);
        }
        else {
            return null;
        }
    }

    /**
     * returns fragment with name @param
     * @param fragment
     * @return
     */
    public Integer getFragmentNumber(Fragment fragment) {
        if(mFragmentNumbers.containsKey(fragment)) {
            return mFragmentNumbers.get(fragment);
        }
        else {
            return null;
        }
    }

    /**
     * returns fragment with name @param
     * @param fragmentNumber
     * @return
     */
    public String getFragmentNumber(Integer fragmentNumber) {
        if(mFragmentNames.containsKey(fragmentNumber)) {
            return mFragmentNames.get(fragmentNumber);
        }
        else {
            return null;
        }
    }


}
