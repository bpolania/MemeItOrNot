package com.lakenewell.memeitornot;

import com.lakenewell.memeitornot.R;
import com.lakenewell.memeitornot.adapters.TabsPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {

	 private ViewPager viewPager;
	    private TabsPagerAdapter mAdapter;
	    private ActionBar actionBar;
	    // Tab titles
	    private String[] tabs = { "Meme it!", "Create a Meme", "Profile" };
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        // Initilization
	        viewPager = (NonSwipeableViewPager) findViewById(R.id.view_pager);
	        actionBar = getActionBar();
	        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
	 
	        viewPager.setAdapter(mAdapter);
	        actionBar.setHomeButtonEnabled(false);
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
	 
	        // Adding Tabs
	        for (String tab_name : tabs) {
	            actionBar.addTab(actionBar.newTab().setText(tab_name)
	                    .setTabListener(this));
	        }
	    }

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(tab.getPosition());
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

}
