
package com.openboxsoftware.battlepong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.Toast;

public class HorizontalPaddle extends Paddle {
	
	public static final int LOCATION_TOP = 0x00000001;
	public static final int LOCATION_BOTTOM = 0x00000002;
        
	protected Paint green;
    
	public HorizontalPaddle(Context context) {
		super(context);
        
        initializeLocation(LOCATION_TOP);
        
        green = new Paint();
        green.setColor(OpenBoxColor.GREEN); 
	}
	
    public HorizontalPaddle(Context context, int location) {
        super(context);
        
        initializeLocation(location);
        
        green = new Paint();
        green.setColor(OpenBoxColor.GREEN); 
    }

    private void initializeLocation(int location) {
		
    	paddle.left = (screenWidth / 2) - ((int)getPaddleWidth() / 2);
    	
    	if(location == LOCATION_TOP) {
	        paddle.top = 0;
	        
		} else if (location == LOCATION_BOTTOM) {
			paddle.top = screenHeight - (int)getPaddleHeight();
			
		} else {
			paddle.top = 0;
		}
	}

	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        
        
        if(paddle.left < 0) {
        	paddle.left = 0;
        }
            
        if((paddle.left + (int)getPaddleWidth()) > screenWidth) {
        	paddle.left = screenWidth - (int)getPaddleWidth();
        }
        
        paddle.right = paddle.left + (int)getPaddleWidth();
        paddle.bottom = paddle.top + (int)getPaddleHeight();
        canvas.drawRect(paddle, green);
        
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
        			paddle.left = x - ((int)getPaddleWidth() / 2);
        			return true;
        		}
        		
        		break;
        	}
        }
        
        return super.onTouchEvent(event);
    }
}
