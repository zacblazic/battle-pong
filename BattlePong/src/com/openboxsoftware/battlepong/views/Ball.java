package com.openboxsoftware.battlepong.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.openboxsoftware.battlepong.BattlePongActivity;


public class Ball extends View {

	private final float BALL_RADIUS = 20f;
	
	private float yVelocity = 13f;
	private float xVelocity = 13f;
	
	private final int screenWidth;
	private final int screenHeight;
	
	private Paint paint;
	
	//private final Paddle[] paddles;
	
	private float x;
	private float y;
	
	private long lastTimeMillis;
	
	private List<VerticalPaddle> paddles = new ArrayList<VerticalPaddle>();
	
	public Ball(Context context, int color) {
		super(context);
		
		screenWidth = ((BattlePongActivity)context).getScreenWidth();
        screenHeight = ((BattlePongActivity)context).getScreenHeight();
        //paddles = ((BattlePongActivity)context).getPaddles();
        
        paint = new Paint();
        paint.setColor(color);
        
        Random random = new Random();
        
        x = random.nextInt(screenWidth);
        y = random.nextInt(screenHeight);
        
        lastTimeMillis = System.currentTimeMillis();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		x += xVelocity;
		y += yVelocity;
		
		if(isNextFrameReady()) 
		{
			if(isTopCollision()) 
			{
				y = BALL_RADIUS;
				yVelocity = -yVelocity;
			}
			
			if(isBottomCollision()) 
			{
				y = screenHeight - BALL_RADIUS;
				yVelocity = -yVelocity;
			}
			
			if(isLeftCollision()) 
			{
				x = BALL_RADIUS;
				xVelocity = -xVelocity;
			}
			
			if(isRightCollision()) 
			{
				x = screenWidth - BALL_RADIUS;
				xVelocity = -xVelocity;
			}
			
			canvas.drawCircle(x, y, BALL_RADIUS, paint);
		}
		notifyObservers();
		
		this.invalidate();
	}

	private boolean isTopCollision() {
		return (y - BALL_RADIUS) <= 0;
	}
	
	private boolean isBottomCollision() {
		return (y + BALL_RADIUS) >= screenHeight;
	}
	
	private boolean isLeftCollision() {
		return (x - BALL_RADIUS) <= 0;
	}
	
	private boolean isRightCollision() {
		return (x + BALL_RADIUS) >= screenWidth;
	}
	
	private boolean isNextFrameReady() {
		long currentTimeMillis = System.currentTimeMillis();
		
		if((currentTimeMillis - lastTimeMillis) >= 1000/125) {
			lastTimeMillis = currentTimeMillis;
			
			return true;
		}
		
		return false;
	}

	public float getX() {return x;}
	public float getY() {return y;}
	public float getXVelocity() {return xVelocity;}
	
	public void addObserver(VerticalPaddle pad)
	{
		paddles.add(pad);
	}
	
	public void notifyObservers()
	{
		for (VerticalPaddle pad : paddles) 
		{
			pad.update(this);
		}
	}
}
