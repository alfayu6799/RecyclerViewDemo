package com.example.recyclerviewdemo;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TestData {

    private static final String TAG = "TestData";

    public TestData(String jsonText){
        try {
            JSONObject object = new JSONObject(jsonText);
            JSONArray array = object.getJSONArray("success");
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
    }

    private int errorCode;

    private List<SwitchItemBean> switchItemList;

    private List<CheckBoxGroup> checkBoxGroupList;

    public static class SwitchItemBean {
        private String key;
        private boolean value;
    }

    public static class CheckBoxGroup {
        private String key;
        private List<String > value;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<SwitchItemBean> getSwitchItemList() {
        return switchItemList;
    }

    public List<CheckBoxGroup> getCheckBoxGroupList() {
        return checkBoxGroupList;
    }

}
