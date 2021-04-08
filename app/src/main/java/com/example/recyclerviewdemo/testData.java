package com.example.recyclerviewdemo;

import java.util.List;

public class testData {

    public testData(String jsonText){

    }

    private int errorCode;

    private List<SwitchItemBean> switchItemList;

    private List<CheckBoxGroup> checkBoxGroupList;

    private class SwitchItemBean {
        private String key;
        private boolean value;
    }

    private class CheckBoxGroup {
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
