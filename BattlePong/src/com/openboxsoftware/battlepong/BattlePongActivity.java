package com.openboxsoftware.battlepong;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.openboxsoftware.battlepong.util.OpenBoxColor;
import com.openboxsoftware.battlepong.views.Ball;
import com.openboxsoftware.battlepong.views.HorizontalPaddle;
import com.openboxsoftware.battlepong.views.Paddle;
import com.openboxsoftware.battlepong.views.VerticalPaddle;

public class BattlePongActivity extends Activity
{
	private int screenWidth;
    private int screenHeight;
    
    private HorizontalPaddle topHorizontalPaddle;
    private HorizontalPaddle bottomHorizontalPaddle;
    private VerticalPaddle leftVerticalPaddle;
    private VerticalPaddle rightVerticalPaddle;
    private Ball redBall;
    private Ball cyanBall;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initializeScreenSize();

        topHorizontalPaddle = new HorizontalPaddle(this, HorizontalPaddle.LOCATION_TOP);
        bottomHorizontalPaddle = new HorizontalPaddle(this, HorizontalPaddle.LOCATION_BOTTOM);
        leftVerticalPaddle = new VerticalPaddle(this, VerticalPaddle.LOCATION_LEFT);
        rightVerticalPaddle = new VerticalPaddle(this, VerticalPaddle.LOCATION_RIGHT);
        redBall = new Ball(this, Color.RED);
        redBall.addObserver(leftVerticalPaddle);
        redBall.addObserver(rightVerticalPaddle);
        
        cyanBall = new Ball(this, Color.CYAN);
        //orangeBall.addObserver(leftVerticalPaddle);
        
        
        
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        layout.setBackgroundColor(OpenBoxColor.GREY);
        layout.addView(topHorizontalPaddle);
        layout.addView(bottomHorizontalPaddle);
        layout.addView(leftVerticalPaddle);
        layout.addView(rightVerticalPaddle);
        layout.addView(redBall);
        layout.addView(cyanBall);
        
        /*for (int i = 0; i < 10000; i++) {
			
			layout.addView(new Ball(this, Color.WHITE));
		}*/
    }
    
    private final void initializeScreenSize() {
        WindowManager wm = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        
        Point size = new Point();
        display.getSize(size);
        
        screenWidth = size.x;
        screenHeight = size.y;
    }
    
    public int getScreenWidth() {
    	return screenWidth;
    }
    
    public int getScreenHeight() {
    	return screenHeight;
    }
    
    public Paddle[] getPaddles() {
    	Paddle[] paddles = new Paddle[4];
    	paddles[Paddle.TOP] = topHorizontalPaddle;
    	paddles[Paddle.BOTTOM] = bottomHorizontalPaddle;
    	paddles[Paddle.LEFT] = leftVerticalPaddle;
    	paddles[Paddle.RIGHT] = rightVerticalPaddle;
    	
    	return paddles;
    }
}
