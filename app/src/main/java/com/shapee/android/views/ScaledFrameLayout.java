package com.shapee.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;


import com.shapee.android.R;


public class ScaledFrameLayout extends FrameLayout {
	private boolean isScaleWidth = true;
	private float widthScale = 1.0f;
	private float heightScale = 1.0f;

	public ScaledFrameLayout(Context context) {
		this(context, null);
	}

	public ScaledFrameLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScaledFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.ScaledFrameLayout, defStyle, 0);
			int scaleType = a.getInt(R.styleable.ScaledFrameLayout_sizeScaleType,
					0);

			isScaleWidth = scaleType == 0;

			widthScale = a.getFloat(R.styleable.ScaledFrameLayout_scaleWidth, 1);
			heightScale = a
					.getFloat(R.styleable.ScaledFrameLayout_scaleHeight, 1);
			a.recycle();
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		float width = getMeasuredWidth();
		float height = getMeasuredHeight();
		if (isScaleWidth) {
			height = width * heightScale * 1.0f / widthScale;
		} else {
			width = height * widthScale * 1.0f / heightScale;
		}

		setMeasuredDimension((int) width, (int) height);
	}

	public void setScale(float widthScale, float heightScale) {
		this.widthScale = widthScale;
		this.heightScale = heightScale;
		invalidate();
	}

	public void setScaleType(boolean isScaleWidth) {
		this.isScaleWidth = false;
		invalidate();
	}
}
