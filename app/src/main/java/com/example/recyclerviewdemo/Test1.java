package com.example.recyclerviewdemo;

import java.util.List;

public class Test1 {

    private List<InfoBean> info;

    public Test1(List<InfoBean> info) {
        this.info = info;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public static class InfoBean {
        /**
         * key : headache
         * value : false
         */

        private String key;
        private boolean value;

        public InfoBean(String key, boolean value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }
}
