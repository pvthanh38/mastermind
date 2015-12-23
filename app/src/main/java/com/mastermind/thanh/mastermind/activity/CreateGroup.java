package com.mastermind.thanh.mastermind.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastermind.thanh.mastermind.R;
import com.mastermind.thanh.mastermind.utils.BackPage;

public class CreateGroup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        //  button back
        BackPage.BackPage((ImageView) findViewById(R.id.back), this);
        //  end back
        TextView btnadd = (TextView) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent animActivity = new Intent(CreateGroup.this,PlayActivity.class);
            startActivity(animActivity);
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
    }
}
