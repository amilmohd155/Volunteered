package com.volunteerx.app.forums;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;

/*
Todo forum_info_ping layout::forum_info_people layout:: forum_info_media layout
 */

public class ForumInfoFragment extends Fragment {

    private static final String TAG = "ForumInfoFragment";

    private androidx.appcompat.widget.Toolbar toolbar;
    private ImageView backButton;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RelativeLayout bottomMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ViewCreating");

        View view = inflater.inflate(R.layout.fragment_forum_info, container, false);

        bottomMenu = (RelativeLayout) view.findViewById(R.id.relLayout3);

        viewPager = (ViewPager) view.findViewById(R.id.wrap_content);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_forum_info);
        backButton = (ImageView) view.findViewById(R.id.back_btn);
        toolbar = (Toolbar) view.findViewById(R.id.forum_info_toolbar);
        if (getActivity() instanceof AppCompatActivity ) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }

        setHasOptionsMenu(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        setupTabLayoutAndViewPager();

        return view;
    }

    private void setupTabLayoutAndViewPager() {


        SectionsStatePagerAdapter sectionsPagerAdapter = new SectionsStatePagerAdapter(getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new ForumPingChildFragment(), "ping");
        sectionsPagerAdapter.addFragment(new ForumPeopleChildFragment(), "people");
        sectionsPagerAdapter.addFragment(new ForumMediaChildFragment(), "media");

        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_ping);
        tabLayout.getTabAt(1).setText(R.string.tab_people);
        tabLayout.getTabAt(2).setText(R.string.tab_media);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (tabLayout.getSelectedTabPosition() != 0) {
                    bottomMenu.setVisibility(View.GONE);
                }
                else {
                    Animation animSlideUp = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_up);
                    bottomMenu.startAnimation(animSlideUp);
                    bottomMenu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_DRAGGING) {
                    bottomMenu.setVisibility(View.GONE);
                }
            }
        });


    }

    /**
     * Function for Creating Options
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forum_info_settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Function for handling option clicks
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
