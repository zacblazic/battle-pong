
package com.openboxsoftware.battlepong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Paddle extends View {
    
	protected float PADDLE_WIDTH_RATIO = 0.35f;
    protected float PADDLE_HEIGHT_RATIO = 0.035f;
    
    protected Rect paddle;
    
    protected int screenWidth;
    protected int screenHeight;
    
    protected int bottomX;
    protected int bottomY;
    
    protected int sideX;
    protected int sideY;
    
    protected boolean touchLocked = false;
    
    public Paddle(Context context) {
        super(context);
        initializeWidthHeight();
        
//        bottomX = (screenWidth / 2) - ((int)getPaddleWidth() / 2);
//        bottomY = screenHeight - (int)getPaddleHeight();
//        
        paddle = new Rect();
//        
//        green = new Paint();
//        green.setColor(OpenBoxColor.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
//        if(paddle.left < 0) {
//                bottomX = 0;
//            }
//            
//            if(paddle.right > screenWidth) {
//                bottomX = screenWidth - (int)getPaddleWidth();
//            }
//        
//        paddle.set(bottomX, bottomY, (int)getPaddleWidth() + bottomX, (int)getPaddleHeight() + bottomY);
//        canvas.drawRect(paddle, green);
//        
//        this.invalidate();
    }

//    @Override
//    public abstract boolean onTouchEvent(MotionEvent event); {
        
//        int action = event.getAction();
//    	
//    	int x = (int)event.getX();
//        int y = (int)event.getY();
//        
//        if(touchLocked) 
//        {
//        	
//        	if(action == MotionEvent.ACTION_UP) 
//        	{
//        		touchLocked = false;
//        	}
//        	
//        	bottomX = x - ((int)getPaddleWidth() / 2);
//        	
//        	return true;
//        	
//        } 
//        else if(x >= paddle.left && x <= paddle.right
//	                && y >= paddle.top && y <= paddle.bottom) 
//        {
//	            
//	        	if(action == MotionEvent.ACTION_DOWN) 
//	        	{
//	        		touchLocked = true;
//	        	}
//	        	
//	            return true;
//        } 
//        else 
//        {
//            return super.onTouchEvent(event);
//        }
    //}
    
    private void initializeWidthHeight() {
        Context context = this.getContext();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        
        screenWidth = size.x;
        screenHeight = size.y;
    }
    
    public final float getPaddleWidth() {
        return screenWidth * PADDLE_WIDTH_RATIO;
    }
    
    public final float getPaddleHeight() {
        return screenHeight * PADDLE_HEIGHT_RATIO;
    }
}
