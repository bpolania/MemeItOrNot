package com.lakenewell.memeitornot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lakenewell.memeitornot.helpers.Utils;

public class FullScreenViewActivity extends Activity {
	
	private ImageView imageView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_activity);
        
        Utils utils = new Utils(FullScreenViewActivity.this);
        
        Display localDisplay = getWindowManager().getDefaultDisplay();
        Point localPoint = new Point();
        localDisplay.getSize(localPoint);
        
     // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("position");

        Bitmap bitmap = BitmapFactory.decodeFile(utils.getFilePaths().get(position));

        ImageView imageView = new ImageView(this);
        Log.d("STOKER","" + imageView.getHeight());
        //
        imageView.setMinimumWidth(localPoint.x);
        imageView.setMinimumHeight(imageView.getMinimumWidth() * bitmap.getHeight()/bitmap.getWidth());
        imageView.setImageBitmap(bitmap);
        imageView.setY((localPoint.y - imageView.getMinimumHeight()) / 2);
        
        RelativeLayout l = ((RelativeLayout)(findViewById(R.id.relativeLayout1)));
        l.addView(imageView);
        
        EditText firstLineEditText = (EditText) findViewById(R.id.firstLineText);
        EditText secondLineEditText = (EditText) findViewById(R.id.editText2);
        
        firstLineEditText.setY(imageView.getY());
        firstLineEditText.bringToFront();
        secondLineEditText.setY(imageView.getY() + imageView.getMinimumHeight() - 220);
        secondLineEditText.bringToFront();
	}
	
	public void onWindowFocusChanged(boolean hasFocus) {
	    // TODO Auto-generated method stub
	    super.onWindowFocusChanged(hasFocus);

	    
	   
        
        
        
        

	        

	    }

}
