package com.lakenewell.memeitornot.tasks;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import 	java.io.InputStream;
import java.net.URL;


/**
 * Class UserLoginTask
 * 
 * This class encapsulates the elements related to the authentication. Extends
 * the {@link AsyncTask} class to run the process in the background, without
 * affecting the main thread.
 * 
 */

public class GetImagesTask extends AsyncTask<ArrayList<String>, String, Void>{

	public Context myCtx;

	public GetImagesTask(Context c)
	{
	  this.myCtx = c;
	}
	
	@Override
    protected Void doInBackground(ArrayList<String>... array) {
    	
    	Bitmap bitmap;
    	
    	ArrayList<Bitmap> responseArray = new ArrayList<Bitmap>();
    	
    	for (String s : array[0]) {
    		
    		String url = "http://i.imgflip.com/" + s + ".jpg";
        	
            HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) new URL(url).openConnection();
				connection.connect();
	            InputStream input = connection.getInputStream();
	            bitmap = BitmapFactory.decodeStream(input);
	            FileOutputStream localFileOutputStream = new FileOutputStream(new File(myCtx.getFilesDir(), s + ".jpg"));
	            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
	            
	            responseArray.add(bitmap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		
    	}

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}