package com.mastermind.thanh.mastermind.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mastermind.thanh.mastermind.R;
import com.mastermind.thanh.mastermind.application.BaseApplication;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseApplication.getListGroups();
        TextView multiplay = (TextView) findViewById(R.id.multiplay);
        multiplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent animActivity = new Intent(MainActivity.this,GroupSearch.class);
            startActivity(animActivity);
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
    }
}
