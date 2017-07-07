package com.example.snail.scrolltextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by snail on 2017/7/7.
 */

public class DoubleVertical extends ViewSwitcher {

    private View view;
    private Timer mTimer;

    public DoubleVertical(Context context) {
        super(context);
    }

    public DoubleVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                view = LayoutInflater.from(getContext()).inflate(R.layout.notice_layout, null);
                return view;
            }
        });

        //设置切入动画
        TranslateAnimation animationTop = new TranslateAnimation(0, 0, 200, 0);
        animationTop.setFillAfter(true);
        animationTop.setDuration(500);
        //设置切出动画
        TranslateAnimation animationBottom = new
                TranslateAnimation(0, 0, 0, -200);
        animationBottom.setFillAfter(true);
        animationBottom.setDuration(500);
        setInAnimation(animationTop);
        setOutAnimation(animationBottom);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ((TextView) getNextView().findViewById(R.id.notice_up_tv)).setText("公告标题");
            ((TextView) getNextView().findViewById(R.id.time_up_tv)).setText("05-26");
            ((TextView) getNextView().findViewById(R.id.notice_down_tv)).setText("公告标题");
            ((TextView) getNextView().findViewById(R.id.time_down_tv)).setText("05-26");
            showNext();
        }
    };

    public void start() {
        mTimer = new Timer();
        mTimer.schedule(new MyTimerTask(), 1000, 4000);
    }


}
