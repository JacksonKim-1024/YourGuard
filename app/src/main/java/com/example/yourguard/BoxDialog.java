package com.example.yourguard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BoxDialog extends Dialog {
    //控制点击dialog外部是否dismiss
    private boolean isCancelable = true;

    //返回键是否dismiss
    private boolean isCanceledOnTouchOutside = true;

    //Dialog View
    private View view;

    //Dialog弹出位置
    private LocationView locationView = LocationView.CENTER;

    /**
     * @param context 上下文
     * @param view    Dialog View
     */
    public BoxDialog(Context context, View view) {
        super(context, R.style.BoxDialog);
        this.view = view;
    }

    /**
     * @param context      上下文
     * @param view         Dialog View
     * @param locationView Dialog弹出位置
     */
    public BoxDialog(Context context, View view, LocationView locationView) {
        super(context, R.style.BoxDialog);
        this.view = view;
        this.locationView = locationView;
    }


    /**
     * 设置是否可以点击 Dialog View 外部关闭 Dialog
     *
     * @param isCancelable true可关闭，false不可关闭
     */
    public void setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    /**
     * 设置是否可以按返回键关闭 Dialog
     *
     * @param isCanceledOnTouchOutside true可关闭，false不可关闭
     */
    public void setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != view) {
            setContentView(view);
            setCancelable(isCancelable);//点击外部是否可以关闭Dialog
            setCanceledOnTouchOutside(isCanceledOnTouchOutside);//返回键是否可以关闭Dialog
            Window window = this.getWindow();
            assert window != null;
            switch (locationView) {
                case TOP:
                    window.setGravity(Gravity.TOP);
                    break;
                case BOTTOM:
                    window.setGravity(Gravity.BOTTOM);
                    break;
                case CENTER:
                    window.setGravity(Gravity.CENTER);
                    break;
            }
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }

    public enum LocationView {
        CENTER, TOP, BOTTOM
    }
}
