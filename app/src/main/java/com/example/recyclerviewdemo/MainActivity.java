package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonArray;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {

        String myJson = getJsonFromAssets(MainActivity.this,"json.json");

        List<TestData.SwitchItemBean> switchItemBeanList = new ArrayList<>();  //switch用dataSource
        List<TestData.CheckBoxGroup> checkBoxGroupList = new ArrayList<>();   //checkBox用dataSource

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