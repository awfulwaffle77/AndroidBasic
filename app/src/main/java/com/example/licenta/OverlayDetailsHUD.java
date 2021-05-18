package com.example.licenta;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class OverlayDetailsHUD extends View {
    DetailedOverlay overlay;

    public OverlayDetailsHUD(Context context) {
        super(context);
    }

    public OverlayDetailsHUD(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        if(overlay == null){
            return;
        }

        paint.setStyle(Paint.Style.FILL);

        setTextSizeForWidth(paint, 320, "Model: " + overlay.getModelName());
        canvas.drawText("Model: " + overlay.getModelName(), 0, 40, paint);

        setTextSizeForWidth(paint, 170, String.valueOf(overlay.getSpeed()) + "m/s");
        canvas.drawText(String.valueOf(overlay.getSpeed()) + "m/s", 0, 140, paint);
    }

    private static void setTextSizeForWidth(Paint paint, float desiredWidth,
                                            String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
    }

    public void setOverlay(DetailedOverlay overlay) {
        this.overlay = overlay;
    }
}
