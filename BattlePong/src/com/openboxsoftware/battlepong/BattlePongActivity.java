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
        
        Paddle paddle = new Paddle(this);
        
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        layout.setBackgroundColor(OpenBoxColor.GREY);
        layout.addView(paddle);
    }
}
