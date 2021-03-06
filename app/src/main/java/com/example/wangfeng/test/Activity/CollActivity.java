package com.example.wangfeng.test.Activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;

import com.example.wangfeng.test.Adapter.CollAdapter;
import com.example.wangfeng.test.Adapter.CollItemDecoration;
import com.example.wangfeng.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
* CollapsingToolbarLayout提供了一个可以折叠的Toolbar
* （PS : 所有使用scroll flag的view都必须定义在没有使用scroll flag的view的前面，
* 这样才能确保所有的view从顶部退出，留下固定的元素。
* http://developer.android.com/reference/android/support/desig）
* revyvlerView 瀑布流
* */
public class CollActivity extends AppCompatActivity {

    @BindView(R.id.colltoolbar)
    CollapsingToolbarLayout mColltoolbar;
    @BindView(R.id.recycle)
    RecyclerView mRecycle;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coll);
        ButterKnife.bind(this);
        initView();
    }

    private ArrayList<String> initData() {
        ArrayList<String >mDate=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDate.add("NO."+i);
        }
        return mDate;
    }

    private void initView() {
        mColltoolbar.setTitle("CollapsingToolbarLayout");
        mColltoolbar.setExpandedTitleColor(ContextCompat.getColor(this,R.color.colorAccent));
        mColltoolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this,R.color.white));
        mRecycle.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        mRecycle.setAdapter(new CollAdapter(initData()));
        //设置分隔线
        mRecycle.addItemDecoration(new CollItemDecoration(this));

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {//向左滑動退出

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "onTouchEvent: ");
                break;
            case MotionEvent.ACTION_UP:
                endX=event.getX();
                endY=event.getY();
                // Toast.makeText(SnackbarActivity.this,(startX-endX)+" " +(startY-endY), Toast.LENGTH_SHORT).show();
                if (startX>endX&&(startX-endX)>Math.abs(startY-endY)){

                    finish();

                }
                break;
        }

        return false;
    }
}
