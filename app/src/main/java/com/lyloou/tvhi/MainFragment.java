package com.lyloou.tvhi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;

public class MainFragment extends BrowseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private ArrayObjectAdapter mRowsAdapter;
    private PicassoBackgroundManager mBackgroundManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        setupUIElements();

        loadRows();

        setupEventListener();
        mBackgroundManager = new PicassoBackgroundManager(getActivity());
    }

    private void setupEventListener() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /* GridItemPresenter */

        mRowsAdapter.add(new ListRow(new HeaderItem(0, "GridItemPresenter0"), getRowAdapter()));
        mRowsAdapter.add(new ListRow(new HeaderItem(1, "GridItemPresenter2"), getRowAdapter()));
        mRowsAdapter.add(new ListRow(new HeaderItem(2, "CardPresenter"), getImageRowAdapter()));

        /* set */
        setAdapter(mRowsAdapter);
    }

    //    http://pic.netbian.com/4kfengjing/
    static final String[] images = new String[]{
            "http://pic.netbian.com/uploads/allimg/180826/113958-1535254798fc1c.jpg",
            "http://pic.netbian.com/uploads/allimg/170725/103840-15009503208823.jpg",
            "http://pic.netbian.com/uploads/allimg/180808/225345-1533740025a4c8.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/180342-1546682622077d.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/200431-15466898712006.jpg",
            "http://pic.netbian.com/uploads/allimg/171030/221926-1509373166fc9a.jpg",
            "http://pic.netbian.com/uploads/allimg/190105/180342-1546682622077d.jpg",
    };

    private ObjectAdapter getImageRowAdapter() {
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

    @NonNull
    private ArrayObjectAdapter getRowAdapter() {
        GridItemPresenter presenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(presenter);
        gridRowAdapter.add("ITEM 1");
        gridRowAdapter.add("ITEM 2");
        gridRowAdapter.add("ITEM 3");
        return gridRowAdapter;
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle("Hello Android TV!"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object item, RowPresenter.ViewHolder viewHolder1, Row row) {
            if (item instanceof String) { // GridItemPresenter row
                mBackgroundManager.updateBackgroundWithDelay("http://seopic.699pic.com/photo/40006/5720.jpg_wh1200.jpg");
            } else if (item instanceof Movie) { // CardPresenter row
                mBackgroundManager.updateBackgroundWithDelay(((Movie) item).getCardImageUrl());
            }
        }
    }
}