package com.t3h.immunization.customxml;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import com.t3h.immunization.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemBottomBar extends LinearLayout {

    @BindView(R.id.ivImage)
    AppCompatImageView ivImage;
    @BindView(R.id.tvView)
    TextView tvView;
    int colorActive, colorNormal;

    // drawable
    Drawable drawableIconNormal, drawableIconActive;

    Unbinder unbinder;

    public ItemBottomBar(Context context) {
        super(context);
        initView(context, null);
    }

    public ItemBottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ItemBottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemBottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_bar_item, this);
        unbinder = ButterKnife.bind(this, view);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemBottomBar);

        // active
        boolean isActive = typedArray.getBoolean(R.styleable.ItemBottomBar_bottomBarActive, false);

        // icon
        drawableIconNormal = typedArray.getDrawable(R.styleable.ItemBottomBar_bottomBarIconNormal);
        drawableIconActive = typedArray.getDrawable(R.styleable.ItemBottomBar_bottomBarIconActive);

        //color
        colorActive = typedArray.getColor(R.styleable.ItemBottomBar_colorTextActive, 0);
        colorNormal = typedArray.getColor(R.styleable.ItemBottomBar_colorTextNormal, 0);

        tvView.setText(typedArray.getString(R.styleable.ItemBottomBar_bottomContent));

        // set active mode
        setActiveMode(isActive);

        // recycle
        typedArray.recycle();
    }

    /**
     * @param isActive
     */
    public void setActiveMode(boolean isActive) {
        ivImage.setImageDrawable(isActive ? drawableIconActive : drawableIconNormal);
        tvView.setTextColor(isActive ? colorActive : colorNormal);
    }
}
