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

        HorizontalPaddle horPaddleBottom = new HorizontalPaddle(this);
        HorizontalPaddle horPaddleTop = new HorizontalPaddle(this);
        horPaddleTop.setBottomY(0);
        
        VerticalPaddle verPaddleLeft = new VerticalPaddle(this);
        VerticalPaddle verPaddleRight = new VerticalPaddle(this);
        verPaddleRight.setSideX(VerticalPaddle.screenWidth - (int)verPaddleRight.getPaddleWidth());
        
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        layout.setBackgroundColor(OpenBoxColor.GREY);
        layout.addView(horPaddleBottom);
        layout.addView(horPaddleTop);
        layout.addView(verPaddleLeft);
        layout.addView(verPaddleRight);
    }
}
