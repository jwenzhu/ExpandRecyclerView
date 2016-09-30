package com.jwen.expandrecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jwen.expandrecycler.base.ExpandRecyclerView;
import com.jwen.expandrecycler.base.OnIClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Jwen
 * date:2016-09-27.
 */
public class SwipeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipemenu);

        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            stringList.add(i + "");
        }
        ExpandRecyclerView recyclerView = (ExpandRecyclerView) findViewById(R.id.rv_swipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter myAdapter = new MyAdapter(stringList);

        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new OnIClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(SwipeActivity.this,position + "",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
