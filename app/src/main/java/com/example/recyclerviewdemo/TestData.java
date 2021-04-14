package com.example.recyclerviewdemo;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TestData {

    private static final String TAG = "TestData";

    private int errorCode;

    private List<SwitchItemBean> switchItemList;

    private List<CheckBoxGroup> checkBoxGroupList;

    public static class SwitchItemBean {
        private String key;
        private boolean value;

        public String getKey() {
            return key;
        }

        public boolean isValue() {
            return value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(boolean value) {
            this.value = value;
        }

        public SwitchItemBean(String key, boolean value){
            this.key = key;
            this.value = value;
        }

    }

    public static class CheckBoxGroup {
        private String key;
        private List<String > value;
        private String checked;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public List<String> getValue() {
            return value;
        }

        public CheckBoxGroup(String key, List<String> value) {
            this.key = key;
            this.value = value;
        }

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
