package com.timelineauction.activities;

import com.timelineauction.fragments.*;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initActionbar();
	}

	private void initActionbar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		actionBar.setDisplayShowTitleEnabled(true);
		// actionBar.setLogo(R.drawable.rabbit);
		//actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
//		BitmapDrawable background = new BitmapDrawable(
//				BitmapFactory.decodeResource(getResources(),
//						R.drawable.navigation_background));
//		background.setTileModeX(android.graphics.Shader.TileMode.MIRROR);
//		actionBar.setBackgroundDrawable(new ColorDrawable(R.drawable.actionbar_background));

		Tab tab = actionBar
				.newTab()
				.setText(R.string.tab_home)
				.setTabListener(
						new MyTabListener<HomeFragment>(this, "Home",
								HomeFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText(R.string.tab_recommended)
				.setTabListener(
						new MyTabListener<RecommendedFragment>(this, "Recommended",
								RecommendedFragment.class));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText(R.string.tab_local)
				.setTabListener(
						new MyTabListener<LocalFragment>(this, "Local",
								LocalFragment.class));
		actionBar.addTab(tab);

		// tab = actionBar
		// .newTab()
		// .setText(R.string.tab_search)
		// .setTabListener(
		// new MyTabListener<SearchFragment>(this, "Search",
		// SearchFragment.class));
		// actionBar.addTab(tab);
	}

	private static class MyTabListener<T extends Fragment> implements
			TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */

		public MyTabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		/* The following are each of the ActionBar.TabListener callbacks */

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out);
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out);
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
