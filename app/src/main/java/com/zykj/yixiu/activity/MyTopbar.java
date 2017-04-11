package com.zykj.yixiu.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.yixiu.R;

/**
 * Created by zykj on 2017/4/11.
 */

public class MyTopbar extends RelativeLayout {
    //标题
    private String title;
    private float titleSize;
    private int titleBg;
    //左侧
    private String leftTiele;
    private float leftTieleSize;
    private int leftTieleleBg;
    //右侧
    private String rightTiele;
    private float rightTieleSize;
    private int rightTieleBg;

    private TextView Title,LeftTiele,RightTiele;
    public MyTopbar(Context context) {
        super(context);
    }

    public MyTopbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化信息
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        //标题控件取出
        title=array.getString(R.styleable.Topbar_title);
        titleSize=array.getDimension(R.styleable.Topbar_titleSize,0);
        titleBg=array.getColor(R.styleable.Topbar_titleBg,0);
        //左侧空间取出
        leftTiele=array.getString(R.styleable.Topbar_leftTiele);
        leftTieleSize=array.getDimension(R.styleable.Topbar_leftTieleSize,0);
        leftTieleleBg=array.getColor(R.styleable.Topbar_leftTieleBg,0);
        //右侧控件取出
        rightTiele=array.getString(R.styleable.Topbar_rightTiele);
        rightTieleSize=array.getDimension(R.styleable.Topbar_rightTieleSize,0);
        rightTieleBg=array.getColor(R.styleable.Topbar_rightTieleBg,0);

        array.recycle();
        //创建标题
        Title=new TextView(context);
        Title.setText(title);
        Title.setGravity(Gravity.CENTER_VERTICAL);
        Title.setTextSize(titleSize);
        Title.setTextColor(titleBg);
        //左侧
        LeftTiele=new TextView(context);
        LeftTiele.setText(leftTiele);
        LeftTiele.setGravity(Gravity.CENTER_VERTICAL);
        LeftTiele.setTextSize(leftTieleSize);
        LeftTiele.setTextColor(leftTieleleBg);
        //右侧
        RightTiele=new TextView(context);
        RightTiele.setText(rightTiele);
        RightTiele.setGravity(Gravity.CENTER_VERTICAL);
        RightTiele.setTextSize(rightTieleSize);
        RightTiele.setTextColor(rightTieleBg);
        //控制title 的位置
        LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(Title,layoutParams);
        //LeftTiele 的位置
        LayoutParams layoutParams1=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(LeftTiele,layoutParams1);
        //控制RightTiele 的位置
        LayoutParams layoutParams2=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(RightTiele,layoutParams2);
    }

    public MyTopbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
