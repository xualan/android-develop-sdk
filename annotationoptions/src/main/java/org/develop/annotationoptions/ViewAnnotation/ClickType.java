package org.develop.annotationoptions.ViewAnnotation;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:ClickType
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月23日14:46
 * <br/>
 * 功能描述：点击类型
 * <br/>
 */
public enum  ClickType {
    /**
     * 未点击
     */
    DEFAULT,
    /**
     * 点击
     */
    ON_CLICK,
    /**
     * 长按
     */
    ON_LONG_CLICK,
    /**
     * 单击条目
     */
    ON_ITEM_CLICK,
    /**
     * 长按条目
     */
    ON_ITEM_LONG_CLICK,
    /**
     * 选择条目
     */
    ON_ITEM_SELECTED,
    /**
     * 未选择
     */
    ON_ITEM_SELECTED_NOTHING;


    public static String getClickTypeName(ClickType clickType){
        String methodName="";
        switch (clickType){
            case DEFAULT:
                methodName="";
                break;
            case ON_CLICK:
                methodName="onClick";
                break;
            case ON_LONG_CLICK:
                methodName="onLongClick";
                break;
            case ON_ITEM_CLICK:
                methodName="onItemClick";
                break;
            case ON_ITEM_LONG_CLICK:
                methodName="onItemLongClick";
                break;
            case ON_ITEM_SELECTED:
                methodName="onItemSelected";
                break;
            case ON_ITEM_SELECTED_NOTHING:
                methodName="onNothingSelected";
                break;
            default:
                break;
        }
        return methodName;
    }

}