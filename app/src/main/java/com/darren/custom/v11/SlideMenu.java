package com.darren.custom.v11;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import androidx.core.view.ViewCompat;

import com.darren.custom.R;

/**
 * date  5/20/21  3:57 PM
 * author  DarrenHang
 */
public class SlideMenu extends HorizontalScrollView {

    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;
    //手势处理
    private GestureDetector mGestureDetector;
    //菜单是否打开
    private boolean mMenuIsOpen = false;
    //是否拦截
    private boolean mIsIntercept = false;
    //阴影
    private View mShadowView;

    public SlideMenu(Context context) {
        this(context, null);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMenu(context, attrs);
    }

    private void initMenu(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideMenu);
        float rightPadding = array.getDimension(R.styleable.SlideMenu_rightPadding, dp2px(50));
        mMenuWidth = (int) (getScreenWidth() - rightPadding);
        array.recycle();
        //初始化手势
        mGestureDetector = new GestureDetector(context, new GestureListener());
    }

    /**
     * DP转换成PX
     *
     * @param sp
     * @return
     */
    private int dp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, getResources().getDisplayMetrics());
    }


    /**
     * 获取屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        Resources resources = this.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取最外层布局
        ViewGroup container = (ViewGroup) this.getChildAt(0);
        //统计子布局的数量
        int containerChildCount = container.getChildCount();
        if (containerChildCount > 2) {
            throw new IllegalStateException("SlideMenu ChildView More Than 2");
        }
        //获取菜单布局
        mMenuView = container.getChildAt(0);
        //菜单宽度 = 屏幕宽度 - 右边 Padding
        mMenuView.getLayoutParams().width = mMenuWidth;
        //获取内容布局
        mContentView = container.getChildAt(1);
        //获取 LayoutParams
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        //移除原来内容布局
        container.removeView(mContentView);
        //创建新的 RelativeLayout
        RelativeLayout contentView = new RelativeLayout(getContext());
        //创建阴影布局
        mShadowView = new View(getContext());
        //设置阴影颜色
        mShadowView.setBackgroundColor(Color.parseColor("#80000000"));
        //设置透明度
        mShadowView.setAlpha(0.0f);
        //将内部加入布局
        contentView.addView(mContentView);
        //将阴影加入布局
        contentView.addView(mShadowView);
        //内容宽度 = 屏幕宽度
        layoutParams.width = getScreenWidth();
        //设置 layoutParams
        contentView.setLayoutParams(layoutParams);
        container.addView(contentView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            //显示内容
            scrollTo(mMenuWidth, 0);
        }
    }

    //手势处理
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (mMenuIsOpen) {
                //当往左滑动时关闭菜单
                if (velocityX < 0) {
                    closeMenu();
                    return true;
                }
            } else {
                //当往右滑动时打开菜单
                if (velocityX > 0) {
                    openMenu();
                    return true;
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public void openMenu() {
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }

    public void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //如果点击被拦截，就不触发之后的操作
        if (mIsIntercept) {
            return true;
        }

        //触发快速滑动，下面就不要执行
        if (mGestureDetector.onTouchEvent(ev)) {
            return true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                // 手指抬起获取滚动的位置
                int currentScrollX = getScrollX();
                if (currentScrollX > mMenuWidth / 2) {
                    // 关闭菜单
                    closeMenu();
                } else {
                    // 打开菜单
                    openMenu();
                }
                return false;
        }

        return super.onTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIsIntercept = false;
        if (mMenuIsOpen) {
            float currentX = ev.getX();
            //当触摸的X大于菜单的宽度
            if (currentX > mMenuWidth) {
                //关闭菜单
                closeMenu();
                //拦截事件，不让子View处理
                mIsIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = 1f * l / mMenuWidth;
        float alphaScale = 1 - scale;
        mShadowView.setAlpha(alphaScale);
    }
}
