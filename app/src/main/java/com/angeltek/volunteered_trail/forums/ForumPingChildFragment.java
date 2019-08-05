package com.angeltek.volunteered_trail.forums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.GridImageAdapter;
import com.angeltek.volunteered_trail.utils.ScrollableGridView;
import com.angeltek.volunteered_trail.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ForumPingChildFragment extends Fragment {

    private static final String TAG = "ForumPingChildFragment";

    private RelativeLayout documentNavigator;
    private TextView showAllMedia;
    private GridView mediaGrid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_ping, container, false);

        documentNavigator = (RelativeLayout) view.findViewById(R.id.heading_1);
        showAllMedia = (TextView) view.findViewById(R.id.show_all_media);
        mediaGrid = (GridView) view.findViewById(R.id.media_grid_ping_tab);


        showAllMedia.setVisibility(View.GONE);

        /**
         * opens new activity/fragment for viewing documents
         */
        documentNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        initImageLoader();
        tempGridSetup();

        return view;

    }

    private void initImageLoader() {

        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getContext());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());

    }

    private void tempGridSetup() {
        ArrayList<String> imgURLs = new ArrayList<>();

        /**
         * Check if imgUrls are greater than 6 or not.
         * if greater than 6 :: Unhide showAll Option and display only 6 images
         * else less than 6 :: hide showAll option and show all 6 images
         */

//        imgURLs.add("http://www.khayavolunteer.com/images/blog/happy-volunteers1.jpg");
//        imgURLs.add("http://moneycrashers-sparkchargemedia.netdna-ssl.com/wp-content/uploads/2014/07/local-school-volunteer-810x455.jpg");
//        imgURLs.add("http://europa.eu/youth/sites/default/files/article/113314864%20-%20%C2%A9%20shutterstock.com%20-%20mangostock%20_9.jpg");
//        imgURLs.add("http://www.hindustantimes.com/rf/image_size_960x540/HT/p2/2016/12/28/Pictures/jewel-in-the-crown_d6a83268-ccef-11e6-b3cb-dcd306bf19b8.JPG");
//        imgURLs.add("http://dmb-shanghai.com/wp-content/uploads/2018/02/larger.jpg");
//        imgURLs.add("http://www.worldtravelguide.net/wp-content/uploads/2017/05/shu-Art-Culture-259104266-Mila-Supinskaya-Glashchenko-copy.jpg");
        imgURLs.add("http://indiaeve.com/images/events/Event430285515Img.jpg");
        imgURLs.add("http://www.nccp.org/publications/images/istock-839295596.jpg");
        imgURLs.add("http://www.arunachalpradesh.gov.in/wp-content/uploads/2017/07/WWD.jpg");
        imgURLs.add("http://static.parade.com/wp-content/uploads/2018/02/city-crosswalk-people-busy-ftr.jpg");
        imgURLs.add("http://trovatten.com/wp-content/uploads/2018/05/Street-photography-in-black-and-white.jpg");
        imgURLs.add("http://s3-us-west-2.amazonaws.com/lightstalking-assets/wp-content/uploads/2017/06/14054143/8701716741_87887d852d_k.jpg");

        setupMediaGrid(imgURLs);

    }

    /**
     * Handles media from the activity main page and display them here.
     */
    private void setupMediaGrid(ArrayList<String> imgURLs) {

        Log.d(TAG, "setupMediaGrid: creating grid view");

        GridImageAdapter adapter = new GridImageAdapter(getContext(), R.layout.layout_grid_mediaview, "", imgURLs);
        mediaGrid.setAdapter(adapter);
    }
}
