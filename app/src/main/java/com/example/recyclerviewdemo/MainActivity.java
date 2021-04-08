package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView, getRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {
        String myJson = getJsonFromAssets(MainActivity.this,"json.json");



        try {
            JSONObject jsonObject = new JSONObject(myJson.toString());
            JSONArray array = jsonObject.getJSONArray("success");
            for (int i = 0; i < array.length(); i++){
                JSONObject newObject = array.getJSONObject(i);
                String key = newObject.getString("key");
                Object value = newObject.get("value");
                if (value instanceof Boolean){

                }else if (value instanceof JSONArray){

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SwitchItemAdapter adapter = new SwitchItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(this);
        getRecyclerView.setAdapter(checkBoxAdapter);
        getRecyclerView.setHasFixedSize(true);
        getRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        recyclerView = findViewById(R.id.rvSymptomUp);
        getRecyclerView = findViewById(R.id.rvSymptomDown);
    }

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