package org.develop.annotationoptions.ViewAnnotation;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsSpinner;

import org.develop.annotationoptions.Exception.ViewInjectException;

import java.lang.reflect.Field;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:ViewInjectManager
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月23日15:22
 * <br/>
 * 功能描述：
 * <br/>
 */
public class ViewInjectManager {
    private ViewInjectManager() {
    }

    /**
     * 初始化ViewInject
     *
     * @param activity
     * @throws ViewInjectException
     */
    public static void initViewInject(Activity activity) throws ViewInjectException {
        if (activity == null) {
            throw new ViewInjectException("inject init activity == null");
        }
        Class cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    field.setAccessible(true);

                    if (field.get(activity) != null) {
                        continue;
                    }

                    ViewInject viewInject = field
                            .getAnnotation(ViewInject.class);
                    if (viewInject != null) {
                        int viewId = viewInject.id();
                        field.set(activity, activity.findViewById(viewId));
                        setClickListener(activity, field, viewInject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void setClickListener(Object object, Field field, ViewInject viewInject) throws Exception {
        ClickType clickType = viewInject.clickType();
        if (field == null || clickType == null) {
            return;
        }
        Object obj = field.get(object);
        switch (clickType) {
            case DEFAULT:
                if (!TextUtils.isEmpty(viewInject.onClick())) {
                    if (obj instanceof View) {
                        ((View) obj).setOnClickListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onClick()));
                    }
                }
                if (!TextUtils.isEmpty(viewInject.onLongClick())) {
                    if (obj instanceof View) {
                        ((View) obj).setOnLongClickListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onLongClick()));
                    }
                }
                if (!TextUtils.isEmpty(viewInject.onItemClick())) {
                    if (obj instanceof AbsListView) {
                        ((AbsListView) obj).setOnItemClickListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onItemClick()));
                    }
                }
                if (!TextUtils.isEmpty(viewInject.onItemLongClick())) {
                    if (obj instanceof AbsListView) {
                        ((AbsListView) obj)
                                .setOnItemLongClickListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onItemLongClick()));
                    }
                }
                if (!TextUtils.isEmpty(viewInject.onItemSelected())) {
                    if (obj instanceof AbsSpinner) {
                        ((AbsSpinner) obj)
                                .setOnItemSelectedListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onItemSelected()));
                    }
                }
                if (!TextUtils.isEmpty(viewInject.onNothingSelected())) {
                    if (obj instanceof AbsSpinner) {
                        ((AbsSpinner) obj)
                                .setOnItemSelectedListener(new OnClickEventListener(object).setCustomMethodName(viewInject.onNothingSelected()));
                    }
                }
                break;
            case ON_CLICK:
                if (obj instanceof View) {
                    ((View) obj).setOnClickListener(new OnClickEventListener(object));
                }
                break;
            case ON_LONG_CLICK:
                if (obj instanceof View) {
                    ((View) obj).setOnLongClickListener(new OnClickEventListener(object));
                }
                break;
            case ON_ITEM_CLICK:
                if (obj instanceof AbsListView) {
                    ((AbsListView) obj).setOnItemClickListener(new OnClickEventListener(object));
                }
                break;
            case ON_ITEM_LONG_CLICK:
                if (obj instanceof AbsListView) {
                    ((AbsListView) obj)
                            .setOnItemLongClickListener(new OnClickEventListener(object));
                }
                break;
            case ON_ITEM_SELECTED:
                if (obj instanceof AbsSpinner) {
                    ((AbsSpinner) obj)
                            .setOnItemSelectedListener(new OnClickEventListener(object));
                }
                break;
            default:
                break;
        }


    }

}