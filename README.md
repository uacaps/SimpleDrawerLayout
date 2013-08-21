SimpleDrawerLayout
==================

This is an implementation of Android's new DrawerLayout

**activity_main.xml** 
First add the DrawerLayout widget on to your Activity.

```xml
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <!-- The main content view -->

    <FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    <!-- The navigation drawer -->

    <ListView
        android:id="@+id/left_drawer"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#eee"
        android:choiceMode="singleChoice"
        android:dividerHeight="1sp" />

</android.support.v4.widget.DrawerLayout>
```

**MainActivty.java**

Next initialize the DrawerLayout and all of the moving parts that goes with it. 

```java
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
	
	private DrawerListener setUpDrawerToggle() {
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
```

Drawer Syncing

```java
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
```

Used to save info on Config Changes (aka Screen rotation and such)

```java
@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
```
