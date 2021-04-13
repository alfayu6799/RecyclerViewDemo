package com.example.recyclerviewdemo;

import java.util.List;

public class Test2 {

    private List<InfoBean> info;

    public Test2(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * key : sputum,color
         * value : ["none","green","yellow","white","rustColor","grayBlack"]
         */

        private String key;
        private List<String> value;

        public InfoBean(String key , List<String> value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
