package com.lakenewell.memeitornot;

import com.lakenewell.memeitornot.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MemeItFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.meme_it_fragment, container, false);
         
        return rootView;
    }
}