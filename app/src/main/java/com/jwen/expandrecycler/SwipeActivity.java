package com.jwen.expandrecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jwen.expandrecycler.base.ExpandRecyclerView;
import com.jwen.expandrecycler.base.OnIClickListener;
import com.jwen.expandrecycler.base.OnItemClickListener;
import com.jwen.expandrecycler.base.OnLongClickListener;
import com.jwen.expandrecycler.swipemenu.OnMenuClickListener;

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
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(SwipeActivity.this,"item" + position,Toast.LENGTH_SHORT).show();
            }
        });
        myAdapter.setOnMenuClickListener(new OnMenuClickListener() {
            @Override
            public void onMenuClick(String tag,int position) {
                Toast.makeText(SwipeActivity.this,tag + "+" + position,Toast.LENGTH_SHORT).show();
            }
        });
        myAdapter.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public void onLongClick(int position) {
                Toast.makeText(SwipeActivity.this,"long" + position,Toast.LENGTH_SHORT).show();
            }
        });
        myAdapter.setOnIClickListener(new OnIClickListener() {
            @Override
            public void onIClick(int position, View view) {
                Toast.makeText(SwipeActivity.this,view + "click" + position,Toast.LENGTH_SHORT).show();
            }
        });

    }


}
