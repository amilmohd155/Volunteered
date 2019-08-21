package com.angeltek.volunteered_trail.forums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.GridImageAdapter;
import com.angeltek.volunteered_trail.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class ForumMediaChildFragment extends Fragment {

    private static final String TAG = "ForumMediaChildFragment";
    private static final int NUM_COL = 3;

    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_media, container, false);

        gridView = (GridView) view.findViewById(R.id.media_grid);
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_COL;

        gridView.setNumColumns(NUM_COL);

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
        imgURLs.add("http://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        imgURLs.add("http://images.unsplash.com/photo-1535498730771-e735b998cd64?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        imgURLs.add("http://cdn.aarp.net/content/dam/aarp/work/on-the-job/2015-08/740-Volunteer-your-way-to-next-job.imgcache.rev3e04f10287417ea880532326d1613331.jpg/jcr:content/renditions/cq5dam.web.420.270.jpeg");
        imgURLs.add("http://www.khayavolunteer.com/images/blog/happy-volunteers1.jpg");
        imgURLs.add("http://moneycrashers-sparkchargemedia.netdna-ssl.com/wp-content/uploads/2014/07/local-school-volunteer-810x455.jpg");
        imgURLs.add("http://europa.eu/youth/sites/default/files/article/113314864%20-%20%C2%A9%20shutterstock.com%20-%20mangostock%20_9.jpg");
        imgURLs.add("http://www.hindustantimes.com/rf/image_size_960x540/HT/p2/2016/12/28/Pictures/jewel-in-the-crown_d6a83268-ccef-11e6-b3cb-dcd306bf19b8.JPG");
        imgURLs.add("http://dmb-shanghai.com/wp-content/uploads/2018/02/larger.jpg");
        imgURLs.add("http://www.worldtravelguide.net/wp-content/uploads/2017/05/shu-Art-Culture-259104266-Mila-Supinskaya-Glashchenko-copy.jpg");
        imgURLs.add("http://indiaeve.com/images/events/Event430285515Img.jpg");
        imgURLs.add("http://www.nccp.org/publications/images/istock-839295596.jpg");
        imgURLs.add("http://www.arunachalpradesh.gov.in/wp-content/uploads/2017/07/WWD.jpg");
        imgURLs.add("http://static.parade.com/wp-content/uploads/2018/02/city-crosswalk-people-busy-ftr.jpg");
        imgURLs.add("http://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEQTpUJebIacK2UYYR3TICQv8BZ9EBD-66fOkCyb2RT_uwT-3w");
        imgURLs.add("http://s3-us-west-2.amazonaws.com/lightstalking-assets/wp-content/uploads/2017/06/14054143/8701716741_87887d852d_k.jpg");

        setupMediaGrid(imgURLs);

    }

    private void setupMediaGrid(ArrayList<String> imgURLs) {

        Log.d(TAG, "setupMediaGrid: creating grid view");

        GridImageAdapter adapter = new GridImageAdapter(getContext(), R.layout.layout_grid_mediaview, "", imgURLs);
        gridView.setAdapter(adapter);

    }


}
