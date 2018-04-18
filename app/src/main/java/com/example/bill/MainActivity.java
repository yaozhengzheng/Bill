package com.example.bill;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.swipeRecyclerView)
    SwipeRecyclerView swipyRefreshLayout;

    ArrayList<String> list;
    private ListAdapter adapter;
    private int offset = 0;//从哪条数据开始取
    private int limit = 10; //共取多少条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        loadData();
        initView();
    }

    private void initView() {
        list = new ArrayList<>();


        swipyRefreshLayout.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        //set layoutManager
        swipyRefreshLayout.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));

        final TextView textView = new TextView(this);
        textView.setText("什么都没有");
        swipyRefreshLayout.setEmptyView(textView);
        adapter = new ListAdapter(this, list);
        swipyRefreshLayout.setAdapter(adapter);

        adapter.setItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        swipyRefreshLayout.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < limit; i++) {
                            list.clear();
//                            ListBean bean = new ListBean();
//                            bean.setTime("这是第" + i + "时间");
                            list.add(String.valueOf(i));
                        }
                        swipyRefreshLayout.complete();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        offset = list.size();

                        if (offset >= list.size()) {
                            swipyRefreshLayout.onNoMore("-- 我也是有底线的 --");
                        } else {
                            swipyRefreshLayout.stopLoadingMore();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, 1000);
            }
        });
    }


    @OnClick({R.id.img_back, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                startActivity(new Intent(this, BuildActivity.class));
                break;
        }
    }
}
