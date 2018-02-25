package com.tugas.leidy.leidykurniahatika_1202154343_modul3;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Water> mWaterData; //buat inisialisasi array
    private WaterAdapter mAdapter; //buat inisialisasi adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Initialize the ArrayList that will contain the data
        mWaterData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new WaterAdapter(this, mWaterData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

        //inisialisasi adapter untuk recycleView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //menghubungkan adapter dan layout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //membuat inisilaisasi awalnya itu orientasi potrait = 1
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        //mengatur jika landscape
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        //Helper class for creating swipe to dismiss and drag and drop functionality
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
                        | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                //membuat inisialisasi posisi gambar
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                //membuat swap untuk waterdata
                Collections.swap(mWaterData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //menghapus cardview
                mWaterData.remove(viewHolder.getAdapterPosition());

                //pemberitahuan adapter jika cardview dihapus
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        //Attach the helper to the RecyclerView
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        //Get the resources from the XML file
        String[] waterList = getResources().getStringArray(R.array.water_title);
        String[] waterInfo = getResources().getStringArray(R.array.water_info);
        TypedArray waterImageResources = getResources().obtainTypedArray(R.array.water_images);

        //Clear the existing data (to avoid duplication)
        mWaterData.clear();

        // and information about each sport
        for (int i = 0; i < waterList.length; i++) {
            mWaterData.add(new Water(waterList[i], waterInfo[i],
                    waterImageResources.getResourceId(i, 0)));
        }

        //Recycle the typed array
        waterImageResources.recycle();

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
    }

    public void resetWater(View view) {
        initializeData();
    }

}
