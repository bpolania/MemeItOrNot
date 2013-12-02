package com.lakenewell.memeitornot.tasks;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RequestTask extends AsyncTask<String, String, ArrayList<String>>{
	
	public Context myCtx;

	public RequestTask(Context c)
	{
	  this.myCtx = c;
	}

    @Override
    protected ArrayList<String> doInBackground(String... uri) {
    	
    	ArrayList<String> filesInDiskNameList = new ArrayList<String>();
    	
    	File f = new File(myCtx.getFilesDir().getPath());        
    	File file[] = f.listFiles();
    	Log.d("Files", "Size: "+ file.length);
    	for (int i=0; i < file.length; i++)
    	{
    	    Log.d("Files", "FileName:" + file[i].getName());
    	    filesInDiskNameList.add(file[i].getName());
    	}
    	
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        ArrayList<String> responseArray = new ArrayList<String>();
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                
    			String id;
    			JSONObject json = new JSONObject(responseString);
    			JSONArray array = json.getJSONObject("data").getJSONArray("memes");

    			for (int i = 0; i < array.length(); i++) {
    			    JSONObject row = array.getJSONObject(i);
    			    id = row.getString("id");
    			    int iid = Integer.parseInt(id);
    			    String id36 = Integer.toString(iid, 36);
    			    
    			    if(!filesInDiskNameList.contains(id36 + ".jpg")) {
    			    	Log.d("FLAG","FLAG");
    			    	responseArray.add(id36);
    			    } 
    			}
                
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return responseArray;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}
