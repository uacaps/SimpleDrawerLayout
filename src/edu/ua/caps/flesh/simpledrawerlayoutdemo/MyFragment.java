/*  
 * Copyright (c) 2013 Aaron Fleshner. All rights reserved.
 * MyFragment
 * Simple Drawer Layout
 * Created by Aaron Fleshner on 8/21/13. 
 */

package edu.ua.caps.flesh.simpledrawerlayoutdemo;

import com.ua.flesh.simpledrawerlayoutdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment that appears in the "content_frame", shows a string
 */
public class MyFragment extends Fragment {
	public static final String ARG_FRAGMENT_NUMBER = "fragment_number";

	public MyFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
		int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
		String FragmentText = getResources().getStringArray(R.array.NaviDrawerArray)[i];
		((TextView) rootView.findViewById(R.id.textView1)).setText(FragmentText);
		getActivity().setTitle(FragmentText);
		return rootView;
	}
}