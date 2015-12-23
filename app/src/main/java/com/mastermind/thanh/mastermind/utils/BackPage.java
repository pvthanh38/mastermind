package com.mastermind.thanh.mastermind.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.mastermind.thanh.mastermind.R;

/**
 * Created by Thanh on 12/17/2015.
 */
public class BackPage {
    ImageView img;
    public static void BackPage(ImageView img, final Context mcontext){

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.setImageResource(R.drawable.back_select);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        ImageView view = (ImageView) v;
                        view.setImageResource(R.drawable.back_select);
                        view.invalidate();
                        Activity activity = (Activity) mcontext;
                        activity.finish();
                        ((Activity) mcontext).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.setImageResource(R.drawable.back);
                        view.invalidate();
                        break;
                    }
                }

                return true;
            }
        });
    }
}
