
package com.openboxsoftware.battlepong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class HorizontalPaddle extends Paddle {
        
    protected Paint green;
    
    public HorizontalPaddle(Context context) {
        super(context);
        
        bottomX = (screenWidth / 2) - ((int)getPaddleWidth() / 2);
        bottomY = screenHeight - (int)getPaddleHeight();
        
        green = new Paint();
        green.setColor(OpenBoxColor.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if(paddle.left < 0) {
                bottomX = 0;
            }
            
            if(paddle.right > screenWidth) {
                bottomX = screenWidth - (int)getPaddleWidth();
            }
        
        paddle.set(bottomX, bottomY, (int)getPaddleWidth() + bottomX, (int)getPaddleHeight() + bottomY);
        canvas.drawRect(paddle, green);
        
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        int action = event.getAction();
    	
    	int x = (int)event.getX();
        int y = (int)event.getY();
        
        if(touchLocked) {
        	
        	if(action == MotionEvent.ACTION_UP) 
        	{
        		touchLocked = false;
        	}
        	
        	bottomX = x - ((int)getPaddleWidth() / 2);
        	
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
