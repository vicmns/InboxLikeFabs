package com.vicmns.demoinboxlikefab;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vicmns.stackerviewlib.FabStackerAdapter;
import com.vicmns.stackerviewlib.FabStackerItem;
import com.vicmns.stackerviewlib.FabStackerView;
import com.vicmns.stackerviewlib.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private FabStackerView mFabStackerView;
    private List<FabStackerItem> mFabStackerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton    fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        FabStackerAdapter fabStackerAdapter = new FabStackerAdapter();

        mFabStackerItems = new ArrayList<>();
        //mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_NORMAL_TYPE, "NOT SMALL!!!"));
        mFabStackerItems.add(new FabStackerItem.Builder()
                .setFabType(FabStackerItem.FAB_SMALL_TYPE).setFabTag("Small Fab 1")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background)
                .setFabClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Small Fab 1", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        mFabStackerItems.add(new FabStackerItem.Builder()
                .setFabType(FabStackerItem.FAB_SMALL_TYPE).setFabTag("Small Fab 2")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background)
                .setFabClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Small Fab 2", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        mFabStackerItems.add(new FabStackerItem.Builder()
                .setFabType(FabStackerItem.FAB_SMALL_TYPE).setFabTag("Small Fab 3")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background)
                .setFabClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Small Fab 3", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        mFabStackerItems.add(new FabStackerItem.Builder()
                .setFabType(FabStackerItem.FAB_SMALL_TYPE).setFabTag("Small Fab 4")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background)
                .setFabClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Small Fab 4", Toast.LENGTH_SHORT).show();
                    }
                })
                .build());
        mFabStackerItems.add(new FabStackerItem.Builder()
                .setFabType(FabStackerItem.FAB_SMALL_TYPE).setFabTag("Small Fab 5")
                .setFabResourceId(R.drawable.fab_icons)
                .setFabBackgroundResId(R.drawable.fab_background).build());

        fabStackerAdapter.setFabListModel(mFabStackerItems);

        mFabStackerView = new FabStackerView.Builder(this)
                .initAnchoredFab(new FabStackerItem.Builder()
                        .setFabTag("Main Fab!")
                        .setFabResourceId(R.drawable.fab_icons)
                        .setFabBackgroundResId(R.drawable.fab_background)
                        .setFabClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Main Toast!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build())
                .anchorStackToFab(fab1).build();

        mFabStackerView.setFabAdapter(fabStackerAdapter);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFabStackerView.isStackerVisible()) mFabStackerView.hideStacker();
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
        if (mFabStackerView != null && !mFabStackerView.handleBackPress())
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
