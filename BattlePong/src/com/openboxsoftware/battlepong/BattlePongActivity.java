package com.openboxsoftware.battlepong;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class BattlePongActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        HorizontalPaddle horPaddleBottom = new HorizontalPaddle(this, HorizontalPaddle.LOCATION_BOTTOM);
        HorizontalPaddle horPaddleTop = new HorizontalPaddle(this, HorizontalPaddle.LOCATION_TOP);
        
        VerticalPaddle verPaddleLeft = new VerticalPaddle(this, VerticalPaddle.LOCATION_LEFT);
        VerticalPaddle verPaddleRight = new VerticalPaddle(this, VerticalPaddle.LOCATION_RIGHT);
        
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        layout.setBackgroundColor(OpenBoxColor.GREY);
        layout.addView(horPaddleBottom);
        layout.addView(horPaddleTop);
        layout.addView(verPaddleLeft);
        layout.addView(verPaddleRight);
    }
}
