package com.lyloou.tvhi.channelgrid;


import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;

import com.lyloou.tvhi.model.Movie;
import com.lyloou.tvhi.presenter.CardPresenter;

import java.util.Random;

public class ChannelGridFragment extends android.support.v17.leanback.app.VerticalGridFragment {

    private static final String TAG = ChannelGridFragment.class.getSimpleName();
    private static final int NUM_COLUMNS = 3;
    ArrayObjectAdapter imageRowAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setTitle("ChannelGridFragment");
        //setBadgeDrawable(getResources().getDrawable(R.drawable.app_icon_your_company));

        setupFragment();
        setupEventListeners();

        // it will move current focus to specified position. Comment out it to see the behavior.
        // setSelectedPosition(5);
    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);

        imageRowAdapter = getImageRowAdapter();
        setAdapter(imageRowAdapter);
    }

    static final String[] images = new String[]{
            "http://pic.netbian.com/uploads/allimg/180826/113958-1535254798fc1c.jpg",
            "http://pic.netbian.com/uploads/allimg/170725/103840-15009503208823.jpg",
            "http://pic.netbian.com/uploads/allimg/180808/225345-1533740025a4c8.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/180342-1546682622077d.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/200431-15466898712006.jpg",
            "http://pic.netbian.com/uploads/allimg/171030/221926-1509373166fc9a.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/180342-1546682622077d.jpg",
    };

    private ArrayObjectAdapter getImageRowAdapter() {
        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(new CardPresenter());
        for (int i = 0; i < 10; i++) {
            Movie movie = new Movie();

            movie.setCardImageUrl(images[i % images.length]);
            movie.setTitle("title" + i);
            movie.setStudio("studio" + i);
            cardRowAdapter.add(movie);
        }
        return cardRowAdapter;
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private Random random = new Random(47);

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Movie) {
                Movie movie = (Movie) item;
                Movie item1 = new Movie();
                item1.setCardImageUrl("http://pic.netbian.com/uploads/allimg/190105/180342-1546682622077d.jpg");
                item1.setTitle("自己定义");
                item1.setStudio("自己定义的副标题");

                int rand = random.nextInt(100) % 3;
                if (rand == 0) {
                    imageRowAdapter.add(item1);
                } else if (rand == 1) {
                    imageRowAdapter.remove(movie);
                } else {
                    imageRowAdapter.clear();
                    imageRowAdapter.add(item1);
                }
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
        }
    }
}
