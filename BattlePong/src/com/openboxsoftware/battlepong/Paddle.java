
package com.openboxsoftware.battlepong;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Paddle extends View {
    
	private static final int TOUCH_FORGIVENESS = 25;
	
	protected float PADDLE_WIDTH_RATIO = 0.35f;
    protected float PADDLE_HEIGHT_RATIO = 0.035f;
    
    protected int screenWidth;
    protected int screenHeight;
    protected Rect paddle;
    protected boolean touchLocked = false;
    
    public Paddle(Context context) {
        super(context);
        initializeScreenSize();
        
        paddle = new Rect();
    }
    
    private final void initializeScreenSize() {
        Context context = this.getContext();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        
        Point size = new Point();
        display.getSize(size);
        
        screenWidth = size.x;
        screenHeight = size.y;
    }
    
    protected final float getPaddleWidth() {
        return screenWidth * PADDLE_WIDTH_RATIO;
    }
    
    protected final float getPaddleHeight() {
        return screenHeight * PADDLE_HEIGHT_RATIO;
    }
    
    protected boolean isTouched(int x, int y) {
    	return (x >= paddle.left - TOUCH_FORGIVENESS) 
    			&& (x <= paddle.right + TOUCH_FORGIVENESS)
                && (y >= paddle.top - TOUCH_FORGIVENESS) 
                && (y <= paddle.bottom + TOUCH_FORGIVENESS);
    }
}
