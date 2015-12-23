package com.mastermind.thanh.mastermind.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mastermind.thanh.mastermind.R;
import com.mastermind.thanh.mastermind.application.BaseApplication;
import com.mastermind.thanh.mastermind.asynctask.ListGroupAsynctask;
import com.mastermind.thanh.mastermind.constant.APIConstant;
import com.mastermind.thanh.mastermind.models.*;
import com.mastermind.thanh.mastermind.models.ListGroup;
import com.mastermind.thanh.mastermind.utils.BackPage;
import com.mastermind.thanh.mastermind.utils.MD5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlayActivity extends Activity {
    ImageView stork_red, stork_orange, stork_yellow, stork_green, stork_blue, stork_violet, image, img1, img2;
    LinearLayout drag;
    String imgName;
    int x, y;
    int total , failure = 0;
    private Chronometer chronometer;
    String[] rowArray = {"1"};
    public ListView listView;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        listView = (ListView) findViewById(R.id.mobile_list);



       /*ArrayAdapter adapter = new ArrayAdapter<String>(PlayActivity.this, R.layout.layout_row_play, rowArray);
        listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/


        TextView btn = (TextView) findViewById(R.id.btnfinish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View footerView = getLayoutInflater().inflate(R.layout.layout_row_play, null);
                //listView.addFooterView(footerView);
            }
        });


        //loadListGroup();
        //OnLoad();
    }

    public void OnLoad(){
        Exit((TextView) findViewById(R.id.exit)); //exit
        StartClock();//clock
        drag = (LinearLayout)findViewById(R.id.drag);
        DragView(drag);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        stork_red = (ImageView) findViewById(R.id.stork_red);
        stork_orange = (ImageView) findViewById(R.id.stork_orange);
        stork_yellow = (ImageView) findViewById(R.id.stork_yellow);
        stork_green = (ImageView) findViewById(R.id.stork_green);
        stork_blue = (ImageView) findViewById(R.id.stork_blue);
        stork_violet = (ImageView) findViewById(R.id.stork_violet);
        OnT(stork_red, (String) stork_red.getTag());
        OnT(stork_orange, (String) stork_orange.getTag());
        OnT(stork_yellow, (String) stork_yellow.getTag());
        OnT(stork_green, (String) stork_green.getTag());
        OnT(stork_blue, (String) stork_blue.getTag());
        OnT(stork_violet, (String) stork_violet.getTag());
    }
    public void OnT(ImageView img, final String imgNames) {
        image = (ImageView) img;
        img.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                // TODO Auto-generated method stub
                imgName = imgNames;
                Log.d("imgName = ", imgName);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                v.startDrag(data, shadow, null, 0);
                return false;
            }
        });
    }
    public void DragView(LinearLayout drag){
        drag.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        x = (int) event.getX();
                        y = (int) event.getY();
                        Log.d("getX L", Float.toString(x));
                        return (true);

                    case DragEvent.ACTION_DROP: {
                        failure = failure + 1;
                        Log.d("ACTION_DROP", "ACTION_DROP");
                        return (true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        if (x > 320) {
                            Log.d("getX", Float.toString(x));
                            total = total + 1;
                            int suc = total - failure;
                            img1.setImageResource(R.drawable.stork_orange);
                            img2.setImageResource(R.drawable.stork_orange);

                        }
                        return (true);
                    }
                    default:
                        break;
                }
                return true;
            }
        });
    }
    public void Exit( TextView txt){
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animActivity = new Intent(PlayActivity.this, MainActivity.class);
                startActivity(animActivity);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
    }
    public void StartClock(){
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    private void loadListGroup() {
        ListGroup cat = ListGroup.getInstance();
        List<ListGroup> cats = cat.getListGroup();
       // Log.d("Name Group = ", cats.toString());
        Log.d("Name Group = ","sdvdf");
        if (cats != null){
            for (int i = 0; i < cats.size(); i++) {
                Log.d("Name Group = ", cats.get(i).getName());
            }
        }
    }
}
