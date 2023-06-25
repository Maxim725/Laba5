package com.example.laba5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements OnTouchListener
{
    private GestureDetectorCompat myDetector;
    private boolean touchFlag = false;
    private TextView _touchText;
    private TextView _gestureText;
    private TextView _countText;
    private View _touchView;
    private View _gestureView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _touchText = findViewById(R.id.touchText);
        _gestureText = findViewById(R.id.gestureText);
        _countText = findViewById(R.id.touchCount);

        _touchView = findViewById(R.id.touchView);
        _touchView.setOnTouchListener(this);

        _gestureView = findViewById(R.id.gestureView);
        GestureListener myGestures = new GestureListener();
        myDetector = new GestureDetectorCompat(this, myGestures);
        _gestureView.setOnTouchListener((v, event) -> myDetector.onTouchEvent(event));
        myDetector.setOnDoubleTapListener(myGestures);
    }

    public class GestureListener implements
            GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener
    {
        @Override
        public boolean onDown(MotionEvent event)
        {
            _gestureText.append("\n onDown");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY)
        {
            _gestureText.append("\n onFling");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent event)
        {
            _gestureText.setText("Clear and onLongPress");
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2,
                                float distanceX, float distanceY)
        {
            _gestureText.append("\n onScroll");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent event)
        {
            _gestureText.append("\n onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event)
        {
            _gestureText.append("\n onSingleTapUp");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event)
        {
            _gestureText.append("\n onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event)
        {
            _gestureText.append("\n onDoubleTapEvent");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event)
        {
            _gestureText.append("\n onSingleTapConfirmed");
            return true;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        int actionMask = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        switch (actionMask)
        {
            case MotionEvent.ACTION_DOWN:
                touchFlag = true;
                _touchText.append("ACTION_DOWN\n");

            case MotionEvent.ACTION_POINTER_DOWN:
                _touchText.append("ACTION_POINTER_DOWN\n");
                break;

            case MotionEvent.ACTION_UP:
                touchFlag = false;

                if (pointerCount == 1)
                    _countText.setText("Количество касаний: " + 0);

                _touchText.setText("");

            case MotionEvent.ACTION_POINTER_UP:
                _touchText.append("ACTION_POINTER_UP\n");
                break;

            case MotionEvent.ACTION_MOVE:
                _touchText.append("ACTION_MOVE\n");
                break;
        }

        if (touchFlag)
            _countText.setText("Количество касаний: " + pointerCount);

        return true;
    }

}
