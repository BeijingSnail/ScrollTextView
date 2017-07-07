package com.example.snail.scrolltextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by snail on 2017/7/7.
 */

public class DoubleVertical extends ViewSwitcher {
    private String TAG = DoubleVertical.class.getSimpleName();

    /**
     * 动画在Y轴方向上的距离
     */
    private int yDistance = 200;

    /**
     * 动画持续时间
     */
    private int mDuration = 500;

    /**
     * 滚动间隔时间
     */
    private int mSpace = 4000;

    /**
     * 当前条数
     */
    private int mCount = -1;

    private List<NoticeBean> mList;

    public DoubleVertical(Context context) {
        super(context);
        initView();
    }

    public DoubleVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return LayoutInflater.from(getContext()).inflate(R.layout.notice_layout, null);
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ((TextView) getNextView().findViewById(R.id.notice_up_tv)).setText(mList.get(++mCount % mList.size()).getTitle());
            ((TextView) getNextView().findViewById(R.id.time_up_tv)).setText(mList.get(mCount % mList.size()).getDate());
            ((TextView) getNextView().findViewById(R.id.notice_down_tv)).setText(mList.get(++mCount % mList.size()).getTitle());
            ((TextView) getNextView().findViewById(R.id.time_down_tv)).setText(mList.get(mCount % mList.size()).getDate());
            showNext();
            mHandler.sendEmptyMessageDelayed(0X111, mSpace);
        }
    };

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<NoticeBean> list) {
        this.mList = list;
    }

    /**
     * 开始执行滚动
     */
    public void start() {
        initParameter();
        mHandler.sendEmptyMessage(0X111);
    }

    /**
     * 初始化参数
     */
    private void initParameter() {
        //设置切入动画
        TranslateAnimation animationTop = new TranslateAnimation(0, 0, yDistance, 0);
        animationTop.setFillAfter(true);
        animationTop.setDuration(mDuration);
        //设置切出动画
        TranslateAnimation animationBottom = new TranslateAnimation(0, 0, 0, -yDistance);
        animationBottom.setFillAfter(true);
        animationBottom.setDuration(mDuration);
        setInAnimation(animationTop);
        setOutAnimation(animationBottom);
    }

    /**
     * 设置动画在Y轴方向上的距离
     */
    public void setDistance(int yDistance) {
        this.yDistance = yDistance;
    }

    /**
     * 设置动画持续时间
     */
    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 设置滚动间隔时间
     *
     * @param space
     */
    public void setSpace(int space) {
        this.mSpace = space;
    }

    public void stop() {
        mHandler.removeMessages(0X111);
    }

}
