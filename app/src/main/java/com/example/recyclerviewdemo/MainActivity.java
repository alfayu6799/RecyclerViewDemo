package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView, getRecyclerView;

    private Button update;

    private List<TestData.SwitchItemBean> switchItemBeanList = new ArrayList<>();  //switch用dataSource
    private List<TestData.CheckBoxGroup> checkBoxGroupList = new ArrayList<>();   //checkBox用dataSource

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {

        //解析json
        String myJson = getJsonFromAssets(MainActivity.this,"json.json");

        try {
            JSONObject jsonObject = new JSONObject(myJson.toString());
            JSONArray array = jsonObject.getJSONArray("success");
            for (int i = 0; i < array.length(); i++){
                JSONObject newObject = array.getJSONObject(i);
                String key = newObject.getString("key");
                Object value = newObject.get("value");
                if (value instanceof Boolean){
                    boolean booleanValue = newObject.getBoolean("value");
                    switchItemBeanList.add(new TestData.SwitchItemBean(key, booleanValue));
                }else if (value instanceof JSONArray){
                    JSONArray jsonValue = newObject.getJSONArray("value");
                    List<String> listData = new ArrayList<>();
                    for (int k = 0; k < jsonValue.length(); k++){
                        listData.add(jsonValue.getString(k));
                    }
                    checkBoxGroupList.add(new TestData.CheckBoxGroup(key,listData));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SwitchItemAdapter adapter = new SwitchItemAdapter(this,switchItemBeanList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(this, checkBoxGroupList);
        getRecyclerView.setAdapter(checkBoxAdapter);
        getRecyclerView.setHasFixedSize(true);
        getRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        DateTime dt1 = new DateTime();
        String SymptomRecordTime = dt1.toString("yyyy-MM-dd,HH:mm:ss");

        JSONArray array = new JSONArray();

        //switch
        for(int i=0; i < switchItemBeanList.size(); i++){
            JSONObject objectSwitch = new JSONObject();
            try {
                objectSwitch.put("key", switchItemBeanList.get(i).getKey());
                objectSwitch.put("value",switchItemBeanList.get(i).isValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(objectSwitch);
        }

        //checkBox
        for(int j = 0; j < checkBoxGroupList.size(); j++){
            JSONObject objectCheckBox = new JSONObject();
            try {
                objectCheckBox.put("key", checkBoxGroupList.get(j).getKey());

                List<String> list = new ArrayList<>();
                String[] a = checkBoxGroupList.get(j).getChecked().split(",");
                for (int k = 0; k < a.length; k++){
                    list.add(a[k]);
                }

                objectCheckBox.put("value", list);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(objectCheckBox);
        }

        JSONObject finalObject = new JSONObject();
        try {
            finalObject.put("targetId", 8);
            finalObject.put("createDate", SymptomRecordTime);
            finalObject.put("symptoms", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "updateToApi: " + finalObject.toString());
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