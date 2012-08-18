package com.openboxsoftware.battlepong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class VerticalPaddle extends Paddle {
	
	public static final int LOCATION_LEFT = 0x00000001;
	public static final int LOCATION_RIGHT = 0x00000002;
	
	protected Paint paddleColor;

	public VerticalPaddle(Context context) {
		super(context);
		
		PADDLE_HEIGHT_RATIO = 0.25f;
		PADDLE_WIDTH_RATIO = 0.06f;

        initializeLocation(LOCATION_LEFT);
		
        paddleColor = new Paint();
        paddleColor.setColor(Color.argb(255, 137, 27, 145));
	}

	public VerticalPaddle(Context context, int location) {
		super(context);
		
		PADDLE_HEIGHT_RATIO = 0.25f;
		PADDLE_WIDTH_RATIO = 0.055f;
		
		initializeLocation(location);

        paddleColor = new Paint();
        paddleColor.setColor(Color.argb(255, 137, 27, 145));
	}
	
	private void initializeLocation(int location) {

		paddle.top = (screenHeight / 2) - ((int)getPaddleHeight() / 2);
		
		if(location == LOCATION_LEFT) {
			paddle.left = 0;
			
		} else if (location == LOCATION_RIGHT) {
			paddle.left = screenWidth - (int)getPaddleWidth();
			
		} else {
			//paddle.left = 0;
		}	
	}
	
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if(paddle.top < 0) 
        {
            paddle.top = 0;
        }
        else if(paddle.bottom > screenHeight) 
        {
            paddle.bottom = screenHeight - (int)getPaddleHeight();
        }
        
        paddle.right = paddle.left + (int)getPaddleWidth();
        paddle.bottom = paddle.top + (int)getPaddleHeight();
        canvas.drawRect(paddle, paddleColor);
        
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        int action = event.getAction();
    	
    	int x = (int)event.getX();
        int y = (int)event.getY();
        
        if(touchLocked) 
        {
        	
        	if(action == MotionEvent.ACTION_UP) 
        	{
        		touchLocked = false;
        	}
        	
        	paddle.top = y - ((int)getPaddleHeight() / 2);
        	
        	return true;
        	
        } 
        else if(x >= paddle.left && x <= paddle.right
	                && y >= paddle.top && y <= paddle.bottom) 
        {
	            
        	if(action == MotionEvent.ACTION_DOWN) 
        	{
        		touchLocked = true;
        	}
        	
            return true;
        }
        else
        {
            return super.onTouchEvent(event);
        }
    }

}
