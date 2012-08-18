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
			paddle.left = 0;
		}	
	}
	
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if(paddle.top < 0) 
        {
            paddle.top = 0;
        }
        else if((paddle.top + (int)getPaddleHeight()) > screenHeight) 
        {
            paddle.top = screenHeight - (int)getPaddleHeight();
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
        
        switch(action) {
        	case MotionEvent.ACTION_DOWN : {
        		if(isTouched(x, y)) {
                	touchLocked = true;
                	
                	return true;
                }
        		
        		break;
        	}
       
        	case MotionEvent.ACTION_UP : {
        		if(touchLocked) {
        			touchLocked = false;
        			return true;
        		}
        		
        		break;
        	}
        	
        	case MotionEvent.ACTION_MOVE : {
        		if(touchLocked) {
        			paddle.top = y - ((int)getPaddleHeight() / 2);
        			return true;
        		}
        		
        		break;
        	}
        }
        
        return super.onTouchEvent(event);
    }
}
