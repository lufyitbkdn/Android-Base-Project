package com.shapee.android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.shapee.android.R;


public class ScaledImageView extends AppCompatImageView {
	private boolean isScaleWidth = true;
	private float widthScale = 1.0f;
	private float heightScale = 1.0f;

	public ScaledImageView(Context context) {
		this(context, null);
	}

	public ScaledImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScaledImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.ScaledImageView, defStyle, 0);
			int scaleType = a.getInt(R.styleable.ScaledImageView_sizeScaleType,
					0);

			isScaleWidth = scaleType == 0;

			widthScale = a.getFloat(R.styleable.ScaledImageView_scaleWidth, 1);
			heightScale = a
					.getFloat(R.styleable.ScaledImageView_scaleHeight, 1);
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
