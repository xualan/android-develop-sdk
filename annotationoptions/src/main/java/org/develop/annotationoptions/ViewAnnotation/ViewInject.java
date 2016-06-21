package org.develop.annotationoptions.ViewAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IDE:Android Studio
 * <br/>
 * ClassName:ViewInject
 * <br/>
 * 作者：xbl
 * <br/>
 * 创建时间：2016年 06月23日14:44
 * <br/>
 * 功能描述：
 * <br/>
 * 如果使用系统自带方法名称，只设置clickType()
 * <br/>
 * 如果使用自定义方法名称，需设置相应的方法名字
 * <br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewInject {
    /**
     * 资源id
     * @return
     */
    int id() default 0;

    /**
     * 点击类型
     * @return
     */
    ClickType clickType() default ClickType.DEFAULT;

    /**
     * 点击方法名称
     * @return
     */
    String onClick() default "";

    /**
     * 长按方法名称
     * @return
     */
    String onLongClick() default "";

    /**
     * 条目点击方法名称
     * @return
     */
    String onItemClick() default "";

    /**
     * 条目长按方法名称
     * @return
     */
    String onItemLongClick() default "";

    /**
     * 条目选择方法名称
     * @return
     */
    String onItemSelected() default "";

    /**
     * 条目未选择方法名称
     * @return
     */
    String onNothingSelected() default "";
}