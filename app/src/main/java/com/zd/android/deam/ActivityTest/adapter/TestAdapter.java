package com.zd.android.deam.ActivityTest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zd.android.deam.R;

import java.util.List;

/**
 * Created by suzy on 2017/3/27.
 **/

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private List<String> list;

    public TestAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testitem, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.tv_test.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    static class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_test;
        public TestViewHolder(View itemView) {
            super(itemView);
            tv_test = (TextView) itemView.findViewById(R.id.tv_text);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Math.random()*1000));
            tv_test.setLayoutParams(params);
        }
    }
}
