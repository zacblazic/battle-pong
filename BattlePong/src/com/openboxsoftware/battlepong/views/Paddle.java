
package com.openboxsoftware.battlepong.views;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.openboxsoftware.battlepong.BattlePongActivity;

public class Paddle extends View {
    
	public static final int TOP = 0x00000001;
	public static final int BOTTOM = 0x00000002;
	public static final int LEFT = 0x00000003;
	public static final int RIGHT = 0x00000004;
	
	private final int TOUCH_FORGIVENESS = 25;
	
	protected float PADDLE_WIDTH_RATIO = 0.35f;
    protected float PADDLE_HEIGHT_RATIO = 0.035f;
    
    protected final int screenWidth;
    protected final int screenHeight;
    
    protected Rect paddle;
    protected boolean touchLocked = false;
    
    public Paddle(Context context) {
        super(context);
        
        screenWidth = ((BattlePongActivity)context).getScreenWidth();
        screenHeight = ((BattlePongActivity)context).getScreenHeight();
        
        paddle = new Rect();
    }
    
    public final float getPaddleWidth() {
        return screenWidth * PADDLE_WIDTH_RATIO;
    }
    
    public final float getPaddleHeight() {
        return screenHeight * PADDLE_HEIGHT_RATIO;
    }
    
    protected boolean isTouched(int x, int y) {
    	return (x >= paddle.left - TOUCH_FORGIVENESS) 
    			&& (x <= paddle.right + TOUCH_FORGIVENESS)
                && (y >= paddle.top - TOUCH_FORGIVENESS) 
                && (y <= paddle.bottom + TOUCH_FORGIVENESS);
    }
}
