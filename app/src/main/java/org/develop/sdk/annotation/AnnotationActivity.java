package org.develop.sdk.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.develop.annotationoptions.ViewAnnotation.ClickType;
import org.develop.annotationoptions.ViewAnnotation.ViewInject;
import org.develop.sdk.BaseActivity;
import org.develop.sdk.R;
import org.develop.sdk.annotation.adapter.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AnnotationActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener {


    @ViewInject(id = R.id.anno_btn_test_onclick, clickType = ClickType.ON_CLICK)
    Button btnClick;
    @ViewInject(id = R.id.anno_btn_test_onclick_2, onClick = "click")
    Button btnClick2;
    @ViewInject(id = R.id.anno_btn_test_onlongclick, clickType = ClickType.ON_LONG_CLICK)
    Button btnLongClick;
    @ViewInject(id = R.id.anno_list_onitemclick, clickType = ClickType.ON_ITEM_CLICK)
    ListView listView;
    @ViewInject(id = R.id.anno_list_onitemlong, clickType = ClickType.ON_ITEM_LONG_CLICK)
    ListView listView2;
    @ViewInject(id = R.id.anno_spinner_onitemselect, clickType = ClickType.ON_ITEM_SELECTED)
    Spinner spinner;

    SimpleListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        initData();

    }


    private void initData(){
        List<String> list=new ArrayList<String>();
        for (int i=0;i<5;i++){
            list.add("item-----"+i);
        }

        listAdapter=new SimpleListAdapter(this,list);
        listView.setAdapter(listAdapter);
        listView2.setAdapter(listAdapter);
        spinner.setAdapter(listAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anno_btn_test_onclick:
                Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "onLongClick", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void click(View v) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "onItemClick", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "onItemLongClick", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "onItemSelected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "onNothingSelected", Toast.LENGTH_SHORT).show();
    }
}
