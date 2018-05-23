package com.example.hotumit.monthlyincome.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotumit.monthlyincome.R;
import com.example.hotumit.monthlyincome.Utility.BaseActivity;
import com.example.hotumit.monthlyincome.Utility.Contextor;
import com.example.hotumit.monthlyincome.Utility.RecyclerItemClickListener;
import com.example.hotumit.monthlyincome.activity.ActivityChoose;
import com.example.hotumit.monthlyincome.dao.NewCustItemCollectionDao;
import com.example.hotumit.monthlyincome.dao.PhotoItemDao;
import com.example.hotumit.mykotlin.adapter.InfoAdapter;
import com.example.hotumit.mykotlin.adapter.NewCusAdapter;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class FragmentCust extends BaseActivity {
    private RecyclerView recyclerView;
    NewCustItemCollectionDao dao;
    NewCusAdapter newcust;
    SwipeRefreshLayout swipeRefreshLayout;
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        initInstances(rootView, savedInstanceState);
        getDataDao();
        return rootView;
    }
*/
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_cust);
    getDataDao();
    initInstances();
    refresh();

}

    private void getDataDao() {
        dao = getIntent().getParcelableExtra("dao");
    }

    private void initInstances() {
        recyclerView = findViewById(R.id.recycler_view_employee_list1);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipLayoutRefresh1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Contextor.getInstance().getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        newcust = new NewCusAdapter(dao,recyclerItemClickListener);
        recyclerView.setAdapter(newcust);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());

    }


    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick() {

        }
    };

    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}