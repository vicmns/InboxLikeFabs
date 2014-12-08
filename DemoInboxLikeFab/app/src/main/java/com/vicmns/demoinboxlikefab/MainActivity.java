package com.vicmns.demoinboxlikefab;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vicmns.stackerviewlib.FabListAdapter;
import com.vicmns.stackerviewlib.FabListModelItem;
import com.vicmns.stackerviewlib.FabStackerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private FabStackerView mFabStackerView;
    private List<FabListModelItem> mFabListModelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        FabListAdapter fabListAdapter = new FabListAdapter();

        mFabListModelItems = new ArrayList<>();
        //mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_NORMAL_TYPE, "NOT SMALL!!!"));
        mFabListModelItems.add(new FabListModelItem.Builder()
                .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Small Fab 1")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());
        mFabListModelItems.add(new FabListModelItem.Builder()
                .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Small Fab 2")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());
        mFabListModelItems.add(new FabListModelItem.Builder()
                .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Small Fab 3")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());
        mFabListModelItems.add(new FabListModelItem.Builder()
                .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Small Fab 4")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());
        mFabListModelItems.add(new FabListModelItem.Builder()
                .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Small Fab 5")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());

        fabListAdapter.setFabListModel(mFabListModelItems);
        mFabStackerView = new FabStackerView.Builder(this)
                .initAnchoredFab(new FabListModelItem.Builder()
                    .setFabType(FabListModelItem.FAB_SMALL_TYPE).setFabTag("Main Fab!")
                    .setFabResourceId(R.drawable.fab_icons)
                    .setFabBackgroundResId(R.drawable.fab_background).build())
                .anchorStackToFab(fab1).build();
        mFabStackerView.setFabAdapter(fabListAdapter);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFabStackerView.isStackerVisible()) mFabStackerView.hideStacker();
                else mFabStackerView.showStacker();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mFabStackerView != null && !mFabStackerView.handleBackPress())
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
