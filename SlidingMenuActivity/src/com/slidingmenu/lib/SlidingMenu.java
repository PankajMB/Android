package com.slidingmenu.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SlidingMenu extends RelativeLayout {
	
	private CustomViewAbove mViewAbove;
	private CustomViewBehind mViewBehind;
	
	public SlidingMenu(Context context) {
		this(context, null);
	}
	
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		LayoutParams behindParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mViewBehind = new CustomViewBehind(context);
		addView(mViewBehind, behindParams);
		LayoutParams aboveParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mViewAbove = new CustomViewAbove(context);
		addView(mViewAbove, aboveParams);
		mViewAbove.setCustomViewBehind(mViewBehind);
		
//		if (mViewAbove == null || mViewBehind == null) {
//			throw new IllegalStateException("id slidingmenuabove and slidingmenubehind must" +
//					" be declared within a SlidingMenu");
//		}
//		
//		// register the CustomViewBehind with the CustomViewAbove
//		mViewAbove.setCustomViewBehind(mViewBehind);
//		
//		// now style everything!
//		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
//		// set the above and behind views if defined in xml
//		int viewAbove = ta.getResourceId(R.styleable.SlidingMenu_viewAbove, -1);
//		if (viewAbove != -1) {
//			LayoutInflater inflater = (LayoutInflater) 
//					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			mViewAbove.setContent(inflater.inflate(viewAbove, this), null);
//		}
//		int viewBehind = ta.getResourceId(R.styleable.SlidingMenu_viewBehind, -1);
//		if (viewBehind != -1) {
//			LayoutInflater inflater = (LayoutInflater) 
//					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			mViewBehind.setContent(inflater.inflate(viewBehind, this));
//		}
//		float offsetBehind = ta.getFloat(R.styleable.SlidingMenu_offsetBehind, 0f);
//		setBehindOffset((int) offsetBehind);
//		float scrollOffsetBehind = ta.getFloat(R.styleable.SlidingMenu_scrollOffsetBehind, 0f);
//		mViewBehind.setScrollScale(scrollOffsetBehind);
	}

	public void registerViews(CustomViewAbove va, CustomViewBehind vb) {
		mViewAbove = va;
		mViewBehind = vb;
		mViewAbove.setCustomViewBehind(mViewBehind);
	}
	
	public void onMeasure(int widthSpec, int heightSpec) {
		super.onMeasure(widthSpec, heightSpec);
	}
	
	public void setAboveContent(View v, ViewGroup.LayoutParams p) {
		mViewAbove.setContent(v, p);
		mViewAbove.invalidate();
		mViewAbove.dataSetChanged();
	}
	
	public void setBehindContent(View v) {
		mViewBehind.setContent(v);
		mViewBehind.invalidate();
		mViewBehind.dataSetChanged();
	}
	
	/**
	 * 
	 * @param b Whether or not the SlidingMenu is in a static mode 
	 * (i.e. nothing is moving and everything is showing)
	 */
	public void setStatic(boolean b) {
		if (b) {
			mViewAbove.setPagingEnabled(false);
			mViewAbove.setCustomViewBehind(null);
			mViewAbove.setCurrentItem(1);
			mViewBehind.setCurrentItem(0);	
		} else {
			mViewAbove.setCurrentItem(1);
			mViewBehind.setCurrentItem(1);
			mViewAbove.setCustomViewBehind(mViewBehind);
			mViewAbove.setPagingEnabled(true);
		}
	}

	/**
	 * Shows the behind view
	 */
	public void showBehind() {
		mViewAbove.setCurrentItem(0);
	}

	/**
	 * Shows the above view
	 */
	public void showAbove() {
		mViewAbove.setCurrentItem(1);
	}

	/**
	 * 
	 * @return Whether or not the behind view is showing
	 */
	public boolean isBehindShowing() {
		return mViewAbove.getCurrentItem() == 0;
	}
	
	/**
	 * 
	 * @param i The margin on the right of the screen that 
	 */
	public void setBehindOffset(int i) {
		((RelativeLayout.LayoutParams)mViewBehind.getLayoutParams()).setMargins(0, 0, i, 0);
	}

	/**
	 * 
	 * @param f The scale of the parallax scroll (i.e. 1.0f scrolls 1 pixel for every
	 * 1 pixel that the above view scrolls and 0.0f scrolls 0 pixels)
	 */
	public void setBehindScrollScale(float f) {
//		mViewBehind.setScrollScale(f);
		mViewAbove.setScrollScale(f);
	}

}
