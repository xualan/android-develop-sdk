# AnnotationOptions #

### 功能支持 ###
- View的子类，单击onClick()，长按onLongClick()
- ListView或者AbsListView的子类的onItemClick()和onItemLongClick()
- AbsSpinner类的子类的onItemSelected()和onNothingSelected()


### 使用方式 ###
- 首先，添加module支持

	```
	compile project(':annotationoptions')
	```

- 调用样式

	调用注解方式：

	调用默认方式，实现相关接口方法

	调用自定义，自定义方法参数与系统方法一致


  - 调用（无点击）

		```
		@ViewInject(id = R.id.anno_btn_test_onclick) 
		Button btnClick;
		```  

  - View调用点击（默认点击）
 
  		`
		@ViewInject(id = R.id.anno_btn_test_onclick, clickType = ClickType.ON_CLICK) 
		`

		`
		Button btnClick;
		`  
  - View调用点击（自定义点击）

		`
		@ViewInject(id = R.id.anno_btn_test_onclick, onClick = "click") 
		`

		`
		Button btnClick;
		`
 
		`
		public void click(View v) {
		`
		`
		        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
		    }
		`

  - View调用长按（默认长按）
 
  		`
		@ViewInject(id = R.id.anno_btn_test_onlongclick, clickType = ClickType.ON_LONG_CLICK) 
		`

		`
		Button btnClick;
		`  
  - View调用长按（自定义长按）

		`
		@ViewInject(id = R.id.anno_btn_test_onlongclick, onLongClick = "longclick") 
		`

		`
		Button btnClick;
		`
 
		`
		public void longclick(View v) {
		`
		`
		        Toast.makeText(this, "longclick", Toast.LENGTH_SHORT).show();
		    }
		`

  - AbsListView调用（默认方法） 

		包括onItemClick和onItemLongClick，使用默认，选择clickType类型
  
		` @ViewInject(id = R.id.anno_list_onitemclick, clickType = ClickType.ON_ITEM_CLICK)
		`

		`
		    ListView listView;
		`
    

  - AbsListView调用（自定义方法）

		同上自定义，自定义的方法名称参数要与系统默认一致
  - AbsSpinner调用（默认方法）
  - AbsSpinner调用（自定义方法）   


### ClickType ###
  


	/*
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





