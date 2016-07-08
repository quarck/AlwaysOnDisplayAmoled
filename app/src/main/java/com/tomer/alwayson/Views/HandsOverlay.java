package com.tomer.alwayson.Views;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.tomer.alwayson.R;

import java.util.Calendar;

import info.staticfree.android.twentyfourhour.overlay.DialOverlay;


public class HandsOverlay implements DialOverlay {

    private final Drawable mHour;
    private final Drawable mMinute;
    private final boolean mUseLargeFace;
    private float mHourRot;
    private float mMinRot;
    private boolean mShowSeconds;

    public HandsOverlay(Context context, boolean useLargeFace) {
        final Resources r = context.getResources();

        mUseLargeFace = useLargeFace;

        mHour = r.getDrawable(R.drawable.hour_hand);
        mMinute = r.getDrawable(R.drawable.minute_hand);
    }

    public HandsOverlay(Drawable hourHand, Drawable minuteHand) {
        mUseLargeFace = false;

        mHour = hourHand;
        mMinute = minuteHand;
    }

    public HandsOverlay(Context context, int hourHandRes, int minuteHandRes) {
        final Resources r = context.getResources();

        mUseLargeFace = false;

        mHour = r.getDrawable(hourHandRes);
        mMinute = r.getDrawable(minuteHandRes);
    }

    public static float getHourHandAngle(int h, int m) {
        return Analog24HClock.is24 ? ((12 + h) / 24.0f * 360) % 360 + (m / 60.0f) * 360 / 24.0f : ((12 + h) / 12.0f * 360) % 360 + (m / 60.0f) * 360 / 12.0f;
    }

    @Override
    public void onDraw(Canvas canvas, int cX, int cY, int w, int h, Calendar calendar,
                       boolean sizeChanged) {

        updateHands(calendar);

        canvas.save();

        if (Analog24HClock.hourOnTop) {
            canvas.rotate(mMinRot, cX, cY);

            if (sizeChanged) {
                w = mMinute.getIntrinsicWidth();
                h = mMinute.getIntrinsicHeight();
                mMinute.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
            }
            mMinute.draw(canvas);
            canvas.restore();
        }

        canvas.rotate(mHourRot, cX, cY);

        if (sizeChanged) {
            w = mHour.getIntrinsicWidth();
            h = mHour.getIntrinsicHeight();
            mHour.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
        }
        mHour.draw(canvas);

        canvas.save();

        if (!Analog24HClock.hourOnTop) {
            canvas.rotate(mMinRot, cX, cY);

            if (sizeChanged) {
                w = mMinute.getIntrinsicWidth();
                h = mMinute.getIntrinsicHeight();
                mMinute.setBounds(cX - (w / 2), cY - (h / 2), cX + (w / 2), cY + (h / 2));
            }
            mMinute.draw(canvas);
            canvas.restore();
        }
    }

    public void setShowSeconds(boolean showSeconds) {
        mShowSeconds = showSeconds;
    }

    private void updateHands(Calendar calendar) {

        final int h = calendar.get(Calendar.HOUR_OF_DAY);
        final int m = calendar.get(Calendar.MINUTE);
        final int s = calendar.get(Calendar.SECOND);

        mHourRot = getHourHandAngle(h, m);
        mMinRot = (m / 60.0f) * 360 + (mShowSeconds ? ((s / 60.0f) * 360 / 60.0f) : 0);
    }

}
