package com.siuyifypcaptcha.physicscaptcha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnGestureListener {
    private GestureDetector gestureDetector;
    private TextView txt;
    private ImageView img;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to generate random images for users to try out
        img = (ImageView) findViewById(R.id.image);
        Random rnd = new Random();

        // drawable folder have six images (img_0, img_1, img_2, img_3, img_4, img_5)
        String imgname = "img_" + rnd.nextInt(6);

        // set the imageView
        img.setImageDrawable(
                getResources().getDrawable(getResourceID(imgname, "drawable",
                        getApplicationContext()))
        );


        //to display text based on the random generated images.
        txt = (TextView) findViewById(R.id.text);

        if (imgname.equals("img_0")){
            txt.setText("Swipe the direction of the red balloon will be travelling once the fan was turned on at its' highest speed.");
        }
        else if (imgname.equals("img_1")){
            txt.setText("Swipe the direction of the skier will be travelling once he leaves the cliff.");
        }
        else if (imgname.equals("img_2")){
            txt.setText("Swipe the direction of the red yarn will be travelling once the cat let go from the catapult.");
        }
        else if (imgname.equals("img_3")){
            txt.setText("Swipe the direction of the blue ball will be travelling after it was thrown against the brick wall at the blue X target by the little boy.");
        }
        else if (imgname.equals("img_4")){
            txt.setText("Swipe the direction of the ball will be travelling once the scissors snips the string attaching to it.");
        }
        else{
            txt.setText("Swipe the direction of the rocket will be travelling once the rock was released from midair and landed onto the pile of rocks.");
        }

        //start gesture detector
        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);
    }

    // swipe directions
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {
        double x1 = motionEvent1.getX();
        double x2 = motionEvent2.getX();
        double y1 = motionEvent1.getY();
        double y2 = motionEvent2.getY();


        // first check what's the current image displayed at random.
        // then check the swipe by the user to see if it matches the expected direction for the displayed image.
        // if match user will be brought to another activity to show that they passed.
        // else they will be brought to another activity, to request them to try again.

        // direction: top left
        if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_0).getConstantState())) && ((x1 > x2) && (y2 < y1))) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }
        // direction: top right
        else if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_1).getConstantState())) && ((x1 < x2) && (y1 > y2))) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        // direction: bottom left
        else if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_2).getConstantState())) && ((y2 > y1) && (x1 > x2))){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        // direction: bottom right
        else if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_3).getConstantState())) && ((x2 > x1) && (y2 > y1))){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }
        // direction: down
        else if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_4).getConstantState())) && (y2 > y1)) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        // direction: up
        else if ((img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_5).getConstantState())) && (y1 > y2)){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            return true;
        }

        else {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
            return false;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    // to return value
    protected final static int getResourceID(final String resName, final String resType, final Context ctx){
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);

        if (ResourceID == 0){
            throw new IllegalArgumentException("No file found.");
        }
        else{
            return ResourceID;
        }
    }
}
