package com.openboxsoftware.battlepong.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class VerticalPaddle extends Paddle {
	
	public static final int LOCATION_LEFT = 0x00000001;
	public static final int LOCATION_RIGHT = 0x00000002;
	
	protected Paint paddleColor;
	
	protected int location;
	
	private long lastAIUpdateMillis;

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
		this.location = location;
        paddleColor = new Paint();
        paddleColor.setColor(Color.argb(255, 137, 27, 145));
        
        lastAIUpdateMillis = System.currentTimeMillis();
	}
	
	private void initializeLocation(int location) {

		paddle.top = (screenHeight / 2) - ((int)getPaddleHeight() / 2);
		
		if(location == LOCATION_LEFT) 
		{
			paddle.left = 0;
		}
		else if (location == LOCATION_RIGHT) 
		{
			paddle.left = screenWidth - (int)getPaddleWidth();
		}
		else 
		{
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
	
	public void update(Ball ball)
	{
		followBall(ball);
	}
	
	public void followBall (Ball ball)
	{
		/*float distance = ball.getX() - paddle.centerX();
		
		if (distance < 0) distance *= -1;
		
		if (distance < screenWidth/2)
		{
			moveTo(ball.getY());
		}*/
		
		if (ball.getXVelocity() > 0 && location == LOCATION_RIGHT)
		{
			moveTo(ball.getY());
		}
		
		else if (ball.getXVelocity() < 0 && location == LOCATION_LEFT)
		{
			moveTo(ball.getY());
		}
	}
	
	private boolean isNextAIUpdateReady() {
		long currentTimeMillis = System.currentTimeMillis();
		
		if((currentTimeMillis - lastAIUpdateMillis) >= 1000/125) {
			lastAIUpdateMillis = currentTimeMillis;
			
			return true;
		}
		
		return false;
	}
	
	public void moveTo(float y)
	{
		float ppu = 18.5F;
	
		if(!isNextAIUpdateReady()) {
			return;
		}
		
		if(paddle.top != (int)(y - (getPaddleHeight()/2)))
		{
			if ((int)(y - (getPaddleHeight()/2)) > paddle.top)
				paddle.top += ppu;
			
			
			if ((int)(y - (getPaddleHeight()/2)) < paddle.top)
				paddle.top -= ppu;
			
			
		}		
	}

}
