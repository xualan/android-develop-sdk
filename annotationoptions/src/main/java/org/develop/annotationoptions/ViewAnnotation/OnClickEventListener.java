package org.develop.annotationoptions.ViewAnnotation;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.develop.annotationoptions.Exception.ViewInjectException;

import java.lang.reflect.Method;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:OnClickEventListener
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月24日14:17
 * <br/>
 * 功能描述：
 * <br/>
 */
public class OnClickEventListener implements View.OnClickListener,View.OnLongClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,AdapterView.OnItemSelectedListener{


    private Object handler;

    public OnClickEventListener(Object handler) {
        this.handler = handler;
    }
    private String methodName;

    /**
     * 设置自定义方法名称
     * @param methodName
     * @return
     */
    public OnClickEventListener setCustomMethodName(String methodName){
        this.methodName=methodName;
        return this;
    }

    @Override
    public void onClick(View v) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_CLICK);
        }else {
            name=methodName;
        }
        invokeClickMethod(handler,name,v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_ITEM_CLICK);
        }else {
            name=methodName;
        }
        invokeItemClickMethod(handler,name,parent,view,position,id);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_ITEM_LONG_CLICK);
        }else {
            name=methodName;
        }
        return invokeItemLongClickMethod(handler,name,parent,view,position,id);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_ITEM_SELECTED);
        }else {
            name=methodName;
        }
        invokeItemSelectMethod(handler,name,parent,view,position,id);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_ITEM_SELECTED_NOTHING);
        }else {
            name=methodName;
        }
        invokeItemSelectNothingMethod(handler,name,parent);
    }

    @Override
    public boolean onLongClick(View v) {
        String name;
        if(TextUtils.isEmpty(methodName)){
            name=ClickType.getClickTypeName(ClickType.ON_LONG_CLICK);
        }else {
            name=methodName;
        }
        return invokeLongClickMethod(handler,name,v);
    }

    private Object invokeClickMethod(Object handler, String methodName,
                                            Object... params) {
        if (handler == null)
            return null;
        Method method = null;
        try {
            method = handler.getClass().getDeclaredMethod(methodName,
                    View.class);
            if (method != null)
                return method.invoke(handler, params);
            else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    private boolean invokeLongClickMethod(Object handler,
                                                 String methodName, Object... params) {
        if (handler == null)
            return false;
        Method method = null;
        try {
            // public boolean onLongClick(View v)
            method = handler.getClass().getDeclaredMethod(methodName,
                    View.class);
            if (method != null) {
                Object obj = method.invoke(handler, params);
                return obj == null ? false : Boolean.valueOf(obj.toString());
            } else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    private Object invokeItemClickMethod(Object handler,
                                                String methodName, Object... params) {
        if (handler == null)
            return null;
        Method method = null;
        try {
            // /onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            method = handler.getClass().getDeclaredMethod(methodName,
                    AdapterView.class, View.class, int.class, long.class);
            if (method != null)
                return method.invoke(handler, params);
            else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private Object invokeItemSelectMethod(Object handler,
                                                String methodName, Object... params) {
        if (handler == null)
            return null;
        Method method = null;
        try {
            method = handler.getClass().getDeclaredMethod(methodName,
                    AdapterView.class, View.class, int.class, long.class);
            if (method != null)
                return method.invoke(handler, params);
            else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    private Object invokeItemSelectNothingMethod(Object handler,
                                                String methodName, Object... params) {
        if (handler == null)
            return null;
        Method method = null;
        try {
            method = handler.getClass().getDeclaredMethod(methodName,
                    AdapterView.class);
            if (method != null)
                return method.invoke(handler, params);
            else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean invokeItemLongClickMethod(Object handler,
                                                     String methodName, Object... params) {
        if (handler == null)
            try {
                throw new ViewInjectException(
                        "invokeItemLongClickMethod: handler is null :");
            } catch (ViewInjectException e) {
                e.printStackTrace();
            }
        Method method = null;
        try {
            method = handler.getClass().getDeclaredMethod(methodName,
                    AdapterView.class, View.class, int.class, long.class);
            if (method != null) {
                Object obj = method.invoke(handler, params);
                return Boolean.valueOf(obj == null ? false : Boolean
                        .valueOf(obj.toString()));
            } else
                throw new ViewInjectException("no such method:" + methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }




}