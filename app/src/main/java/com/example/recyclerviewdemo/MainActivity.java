package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.Element;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView, getRecyclerView;

    private Button update;

    private List<Test1.InfoBean> infoBeanList = new ArrayList<>();
    private List<Test2.InfoBean> infoBeanList2= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initData2();
    }

    private void initData2(){
        List<String> stringList = new ArrayList<>();
        String myJson2 = getJsonFromAssets(MainActivity.this,"test2.json");
        try {
            JSONObject jsonObject2 = new JSONObject(myJson2.toString());
            JSONArray  jsonArray = jsonObject2.getJSONArray("info");
            for (int j =0; j < jsonArray.length(); j++){
                JSONObject object2 = jsonArray.getJSONObject(j);
                String key2 = object2.getString("key");
                Log.d(TAG, "initData2: " + object2.get("value"));

                //infoBeanList2.add(new Test2.InfoBean(key2, stringList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(this, infoBeanList2);
        //getRecyclerView.setAdapter(checkBoxAdapter);
        //getRecyclerView.setHasFixedSize(true);
        //getRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        String myJson1 = getJsonFromAssets(MainActivity.this,"test1.json");
        try {
            JSONObject jsonObject = new JSONObject(myJson1.toString());
            JSONArray array = jsonObject.getJSONArray("info");
            for(int i=0; i < array.length(); i++){
             JSONObject object = array.getJSONObject(i);
             String key = object.getString("key");
             boolean value = (boolean) object.get("value");
                infoBeanList.add(new Test1.InfoBean(key, value));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        String myJson = getJsonFromAssets(MainActivity.this,"json.json");
        TestData testData = new TestData(myJson);

        List<TestData.SwitchItemBean> switchItemBeanList = testData.getSwitchItemList();
        List<TestData.CheckBoxGroup> checkBoxGroupList = testData.getCheckBoxGroupList();

        try {
            JSONObject jsonObject = new JSONObject(myJson.toString());
            JSONArray array = jsonObject.getJSONArray("success");
            for (int i = 0; i < array.length(); i++){
                JSONObject newObject = array.getJSONObject(i);
                String key = newObject.getString("key");
                Object value = newObject.get("value");

                if (value instanceof Boolean){
                    TestData.SwitchItemBean data = new TestData.SwitchItemBean(key, (Boolean) value);
                    switchItemBeanList.add(data);
                }else if (value instanceof JSONArray){
                    checkBoxGroupList.add(new TestData.CheckBoxGroup(key, (List<String>) value));
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

        SwitchItemAdapter adapter = new SwitchItemAdapter(this,infoBeanList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        recyclerView = findViewById(R.id.rvSymptomUp);
        getRecyclerView = findViewById(R.id.rvSymptomDown);
        update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpdate:
                updateToApi();
                break;
        }
    }

    //上傳資料到後端
    private void updateToApi() {
    }

    //取得本地端json檔案
    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

}