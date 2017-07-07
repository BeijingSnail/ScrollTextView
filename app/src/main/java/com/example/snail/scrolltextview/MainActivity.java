package com.example.snail.scrolltextview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<NoticeBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        bindData();
    }

    private void bindData() {
        final DoubleVertical doubleVertical = (DoubleVertical) findViewById(R.id.double_vertical);
        if (beanList != null && beanList.size() > 0) {
            doubleVertical.setData(beanList);
            doubleVertical.start();
        }
    }

    /**
     * 初始化九条数据
     */
    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            beanList.add(new NoticeBean("公告" + (i + 1), "07-" + (i + 1)));
        }
    }
}
