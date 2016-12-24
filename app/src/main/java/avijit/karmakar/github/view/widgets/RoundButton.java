package avijit.karmakar.github.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import avijit.karmakar.github.R;

/**
 * Created by USER on 22-12-2016.
 */

public class RoundButton extends AppCompatButton {

    private GradientDrawable drawable;
    private int solidBackground;

    public RoundButton(Context context) {
        this(context, null);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.round_button);
        initializeAttributes(attrsArray);
        attrsArray.recycle();
    }

    private void initializeAttributes(TypedArray attrsArray) {
        drawable = new GradientDrawable();
        setSolidBackground(attrsArray.getColor(R.styleable.round_button_solid_background,
                ContextCompat.getColor(getContext(), R.color.colorWhite)));
    }

    private void setSolidColor(int background) {
        if (drawable != null) {
            drawable.setColor(background);
        }
    }

    public void setSolidBackground(int solidBackground) {
        this.solidBackground = solidBackground;
        setSolidColor(solidBackground);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        drawable.setCornerRadius(height);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setMeasuredDimension(width, height);
    }

}
