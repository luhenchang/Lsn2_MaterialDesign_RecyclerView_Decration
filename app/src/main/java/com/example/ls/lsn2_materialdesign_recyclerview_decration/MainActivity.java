package com.example.ls.lsn2_materialdesign_recyclerview_decration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecylerview;
    private MyRecyclerAdapter mAdapter;
    private ArrayList<String> mList;
    private RecyclerView.ItemDecoration decor;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setData();

    }


    private void setData() {
        mAdapter = new MyRecyclerAdapter(mList);
        //LayoutManager布局管理器，控制摆放：线性摆放，
        // mRecylerview.setLayoutManager(new GridLayoutManager(this,3));
        // mRecylerview.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        mRecylerview.setLayoutManager(new GridLayoutManager(this, 3));
        mRecylerview.setAdapter(mAdapter);
        mRecylerview.addItemDecoration(new RecylerViewGridViewDivider(this));
/*
        mRecylerview.setLayoutManager(new LinearLayoutManager(this));
        mRecylerview.setAdapter(mAdapter);*/
    //    mRecylerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        mAdapter.setOnclickListener(new MyRecyclerAdapter.ItemOnclickListener() {
            @Override
            public void itemOnclick(View view, int position) {
                Toast.makeText(MainActivity.this, "点我干吗？" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 18; i++) {
            mList.add("item" + i);
        }
    }

    private void initView() {
        mRecylerview = (RecyclerView) findViewById(R.id.recyle_view);
        mList = new ArrayList<>();

    }

    public void additem(View view) {
        mAdapter.addItem(3, "我爱你吆");
    }

    public void removeItem(View view) {
        mAdapter.removeItem(3);
    }

    boolean flag_linear = false;

    public void chagedView(View view) {
        if (decor != null) {
            mRecylerview.removeItemDecoration(decor);
        }
        if (!flag_linear) {
            // mRecylerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
            mRecylerview.setLayoutManager(new GridLayoutManager(this, 3));
            decor = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
            mRecylerview.addItemDecoration(decor);
        } else {
            mRecylerview.setLayoutManager(new LinearLayoutManager(this));
            decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);

            // mRecylerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
            mRecylerview.addItemDecoration(decor);

        }

        flag_linear = !flag_linear;

    }
}
