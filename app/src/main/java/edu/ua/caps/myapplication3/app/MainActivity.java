/*  
 * Copyright (c) 2013 Aaron Fleshner. All rights reserved.
 * MainActivity
 * Simple Drawer Layout
 * Created by Aaron Fleshner on 8/21/13. 
 */

package edu.ua.caps.myapplication3.app;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*built from http://developer.android.com/training/implementing-navigation/nav-drawer.html#top */
public class MainActivity extends FragmentActivity implements OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerListView;
	private String[] mNaviArray;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InitDrawer();
		// Sets the fragment Item to the first fragment if the app is first opened
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	/**
	 * Initialize all of the views needed for the DrawerLayout
	 */
	private void InitDrawer() {
		// Displays the ic_drawer drawable put in the ActionBarDrawerToggle
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// Enables the Home button to be clicked
		getActionBar().setHomeButtonEnabled(true);
		// Initializes the DrawerLayout and ListView in the layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerListView = (ListView) findViewById(R.id.left_drawer);
		// Gets the array from the strings.xml in your res/values folder
		mNaviArray = getResources().getStringArray(R.array.NaviDrawerArray);
		// Sets the Adapter for the ListView
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.drawer_list_item, mNaviArray);
		mDrawerListView.setAdapter(aa);
		// Sets the drawer shadow
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// Set the drawer click listener
		mDrawerLayout.setDrawerListener(setUpDrawerToggle());
		// Set the list's click listener
		mDrawerListView.setOnItemClickListener(this);
	}

	// The provided drawer indicator drawable will animate slightly
	// off-screen as the drawer is opened, indicating that in the open state
	// the drawer will move off-screen when pressed and in the closed state
	// the drawer will move on-screen when pressed.
	private DrawerLayout.DrawerListener setUpDrawerToggle() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}
		};
		return mDrawerToggle;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	// OnItemClickListener
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		selectItem(pos);
	}

	// updates to fragment selected
	private void selectItem(int i) {
		// update the main content by replacing fragments
		Fragment fragment = new MyFragment();
		Bundle args = new Bundle();
		args.putInt(MyFragment.ARG_FRAGMENT_NUMBER, i);
		fragment.setArguments(args);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		// update selected item and title, then close the drawer
		mDrawerListView.setItemChecked(i, true);
		setTitle(mNaviArray[i]);
		mDrawerLayout.closeDrawer(mDrawerListView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
		menu.findItem(R.id.action_github).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		switch (item.getItemId()) {
		case R.id.action_github:
			goToGitHub();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void goToGitHub() {
		Uri uriUrl = Uri.parse("http://github.com/uacaps");
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}
}
