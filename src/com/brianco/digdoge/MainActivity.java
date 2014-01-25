package com.brianco.digdoge;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.BaseInputConnection;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class MainActivity extends AndroidApplication {

	private MyGame game;
    private AndroidApplicationConfiguration cfg;
    private GestureDetector mGestureDetector;
	private BaseInputConnection bic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Glass gestures
    	bic = new BaseInputConnection(this.getWindow().getDecorView(), false);
        mGestureDetector = createGestureDetector(this);
        
        cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;

        game = new MyGame();
        initialize(game, cfg);
    }

    /*
     * Send generic motion events to the gesture detector
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {
                if (gesture == Gesture.TAP) {
                    // do something on tap
                	fakeDown();
                    return true;
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    // do something on right (forward) swipe
                	fakeRight();
                    return true;
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    // do something on left (backwards) swipe
                	fakeLeft();
                    return true;
                }
                return false;
            }
        });
        return gestureDetector;
    }

    private void fakeDown() {
    	long time = SystemClock.uptimeMillis();
    	Log.d("time", "" + time);
    	KeyEvent event2 = new KeyEvent(time, time + 100, KeyEvent.ACTION_DOWN,
    			KeyEvent.KEYCODE_DPAD_DOWN, 0, KeyEvent.META_SYM_ON, 0, 0,
    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
    	bic.sendKeyEvent(event2);

    	fakeDown2();
    }

    private void fakeRight() {
    	long time = SystemClock.uptimeMillis();
    	Log.d("time", "" + time);
    	KeyEvent event2 = new KeyEvent(time, time + 100, KeyEvent.ACTION_DOWN,
    			KeyEvent.KEYCODE_DPAD_RIGHT, 0, KeyEvent.META_SYM_ON, 0, 0,
    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
    	bic.sendKeyEvent(event2);

    	fakeRight2();
    }

    private void fakeLeft() {
    	long time = SystemClock.uptimeMillis();
    	Log.d("time", "" + time);
    	KeyEvent event2 = new KeyEvent(time, time + 100, KeyEvent.ACTION_DOWN,
    			KeyEvent.KEYCODE_DPAD_LEFT, 0, KeyEvent.META_SYM_ON, 0, 0,
    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
    	bic.sendKeyEvent(event2);

    	fakeLeft2();
    }

    private void fakeDown2(){
        new Thread(new Runnable() {
            public void run() {
            	try {
					Thread.sleep(100);

			    	long time = SystemClock.uptimeMillis();
			    	KeyEvent event3 = new KeyEvent(time, time + 100, KeyEvent.ACTION_UP,
			    			KeyEvent.KEYCODE_DPAD_DOWN, 0, KeyEvent.META_SYM_ON, 0, 0,
			    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
			    	bic.sendKeyEvent(event3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }).start();
    }

    private void fakeRight2(){
        new Thread(new Runnable() {
            public void run() {
            	try {
					Thread.sleep(100);

			    	long time = SystemClock.uptimeMillis();
			    	KeyEvent event3 = new KeyEvent(time, time + 100, KeyEvent.ACTION_UP,
			    			KeyEvent.KEYCODE_DPAD_RIGHT, 0, KeyEvent.META_SYM_ON, 0, 0,
			    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
			    	bic.sendKeyEvent(event3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }).start();
    }

    private void fakeLeft2(){
        new Thread(new Runnable() {
            public void run() {
            	try {
					Thread.sleep(100);

			    	long time = SystemClock.uptimeMillis();
			    	KeyEvent event3 = new KeyEvent(time, time + 100, KeyEvent.ACTION_UP,
			    			KeyEvent.KEYCODE_DPAD_LEFT, 0, KeyEvent.META_SYM_ON, 0, 0,
			    			KeyEvent.FLAG_VIRTUAL_HARD_KEY);
			    	bic.sendKeyEvent(event3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }).start();
    }
}
