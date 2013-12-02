package com.lakenewell.memeitornot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;

import com.lakenewell.memeitornot.adapters.GridViewImageAdapter;
import com.lakenewell.memeitornot.helpers.AppConstant;
import com.lakenewell.memeitornot.helpers.Utils;
import com.lakenewell.memeitornot.tasks.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.res.Resources;
import android.util.TypedValue;

public class CreateMemeFragment extends Fragment {
	
	private Utils utils;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewImageAdapter adapter;
    private GridView gridView;
    private int columnWidth;
 
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
 
		View view = inflater.inflate(R.layout.create_meme_fragment, container, false);
		
		gridView = (GridView)view.findViewById(R.id.grid_view);
        
        String URL = "http://api.imgflip.com/get_memes";
        
        //GetImagesTask().execute(new RequestTask().execute(URL).get()).get();
        
        try {
			new GetImagesTask(getActivity()).execute(new RequestTask(getActivity()).execute(URL).get()).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        utils = new Utils(getActivity());
        
        // Initilizing Grid View
        InitilizeGridLayout();
 
        // loading all image paths from SD card
        imagePaths = utils.getFilePaths();
 
        // Gridview adapter
        adapter = new GridViewImageAdapter(getActivity(), imagePaths,
                columnWidth);
 
        // Instance of ImageAdapter Class
        gridView.setAdapter(adapter);
        
        return view;
    }
	
	private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());
 
        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);
 
        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }
}