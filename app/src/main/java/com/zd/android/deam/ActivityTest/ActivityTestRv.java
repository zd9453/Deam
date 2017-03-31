package com.zd.android.deam.ActivityTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.RelativeLayout;

import com.zd.android.deam.ActivityTest.adapter.TestAdapter;
import com.zd.android.deam.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityTestRv extends AppCompatActivity {

    private RecyclerView recycleView;
    private TestAdapter mAdapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rv);


        recycleView = (RecyclerView) findViewById(R.id.mytest);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(4,1));
        mAdapter = new TestAdapter(data);
        recycleView.setAdapter(mAdapter);
        init();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //拖拽
                int tMove = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                //滑动删除  0 --不能删除
                int tdeleta = 0;
                return makeMovementFlags(tMove,tdeleta);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // 拖拽
                // 更新数据、适配器
                Collections.swap(data,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑删除，一般删除数据源，通知适配器更新
            }
        });
        helper.attachToRecyclerView(recycleView);
    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            data.add("test" + i);
        }
        mAdapter.notifyDataSetChanged();
    }



}
