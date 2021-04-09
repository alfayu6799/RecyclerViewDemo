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
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView, getRecyclerView;

    private Button update;

    private ScrollView sv;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.test_layout);

        initView();

        initData();
    }

    private void initData() {
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
                    Switch sw = new Switch(this);
                    sw.setChecked((Boolean) value);
                    if(key.equals("headache")){
                        sw.setText(getString(R.string.headache));
                    } else if (key.equals("fatigue")){
                        sw.setText(getString(R.string.fatigue));
                    } else if (key.equals("soreMusclesJoints")){
                        sw.setText(getString(R.string.soreMusclesJoints));
                    }else if (key.equals("soreThroat")){
                        sw.setText(getString(R.string.soreThroat));
                    }else if (key.equals("cough")){
                        sw.setText(getString(R.string.cough));
                    }else if (key.equals("runnyNose")){
                        sw.setText(getString(R.string.runnyNose));
                    }else if (key.equals("diarrhea")){
                        sw.setText(getString(R.string.diarrhea));
                    }else if (key.equals("chestTightness")){
                        sw.setText(getString(R.string.chestTightness));
                    }else if (key.equals("shortnessBreath")){
                        sw.setText(getString(R.string.shortnessBreath));
                    }else if (key.equals("taste")){
                        sw.setText(getString(R.string.taste));
                    }else if (key.equals("vomiting")){
                        sw.setText(getString(R.string.vomiting));
                    }
                    sw.setTextSize(22f);
                    ll.addView(sw);
                }else if (value instanceof JSONArray){
                    TextView textTitle = new TextView(this);
                    TextView textSub = new TextView(this);
                    String[] str = key.split(","); //以,分割

                    //痰&鼻涕
                    if (str[0].equals("sputum")){
                        textTitle.setText(getString(R.string.sputum));
                    }else if(str[0].equals("nose")){
                        textTitle.setText(getString(R.string.nose));
                    }

                    //顏色&型態
                    if (str[1].equals("color")){
                        textSub.setText(getString(R.string.color));
                    }else if (str[1].equals("type")){
                        textSub.setText(getString(R.string.type));
                    }

                    RadioButton[] rb = new RadioButton[10];
                    RadioGroup rg = new RadioGroup(this); //create the RadioGroup
                    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
                    for(int j=0; j<((JSONArray) value).length(); j++){
                        rb[j]  = new RadioButton(this);
                        rb[j].setText("" + ((JSONArray) value).getString(j));
                        rb[j].setId(i + 100);
                        rg.addView(rb[j]);
                    }

                    textTitle.setTextSize(24f);
                    textSub.setTextSize(22f);
                    ll.addView(textTitle);
                    ll.addView(textSub);
                    ll.addView(rg);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.setContentView(sv);
        //SwitchItemAdapter adapter = new SwitchItemAdapter(this);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(this, checkBoxGroupList);
//        getRecyclerView.setAdapter(checkBoxAdapter);
//        getRecyclerView.setHasFixedSize(true);
//        getRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void initView() {
        sv = new ScrollView(this);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

//        final RadioButton[] rb = new RadioButton[5];
//        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
//        rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
//        for(int i=0; i<5; i++){
//            rb[i]  = new RadioButton(this);
//            rb[i].setText("5");
//            rb[i].setId(i + 100);
//            rg.addView(rb[i]);
//        }
//        ll.addView(rg);

        //this.setContentView(sv);
        //recyclerView = findViewById(R.id.rvSymptomUp);
//        getRecyclerView = findViewById(R.id.rvSymptomDown);
        //update = findViewById(R.id.btnUpdate);
        //update.setOnClickListener(this);
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