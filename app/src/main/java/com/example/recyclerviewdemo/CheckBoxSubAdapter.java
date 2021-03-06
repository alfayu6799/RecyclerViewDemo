package com.example.recyclerviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CheckBoxSubAdapter extends RecyclerView.Adapter<CheckBoxSubAdapter.ViewHolder>{
    private static final String TAG = "CheckBoxSubAdapter";

    private Context context;
    private List<String> value;
    private int subPos;

    private List<TestData.CheckBoxGroup> checkBoxGroupList = new ArrayList<>();

    public CheckBoxSubAdapter(Context context, List<String> value, int subPos, List<TestData.CheckBoxGroup> checkBoxGroupList) {
        this.context = context;
        this.value = value;
        this.subPos = subPos;
        this.checkBoxGroupList = checkBoxGroupList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disase_checkbox_sub_item, parent, false);
        return new CheckBoxSubAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Dictionary dictionary = getDictionary();

        holder.checkBox.setText((CharSequence) dictionary.get(value.get(position)));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){ //有勾選才會寫入
                    if (checkBoxGroupList.get(subPos).getChecked().isEmpty()){
                        checkBoxGroupList.get(subPos).setChecked(value.get(position));
                    }else {
                        checkBoxGroupList.get(subPos).setChecked(checkBoxGroupList.get(subPos).getChecked()+"," + value.get(position));
                    }
                    Log.d(TAG, "勾選:" + checkBoxGroupList.get(subPos).getKey() +",value:" + checkBoxGroupList.get(subPos).getChecked());
                }else {  //選取後又取消時要將原本的資料刪除


                }
            }
        });

    }

    @Override
    public int getItemCount() {
//        return value.size();
        return checkBoxGroupList.get(subPos).getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    private Dictionary getDictionary(){
        Dictionary dictionary = new Hashtable();

        dictionary.put("none","無");
        dictionary.put("green","綠色");
        dictionary.put("yellow","黃色");
        dictionary.put("white","白色");
        dictionary.put("rustColor","鐵鏽色");
        dictionary.put("grayBlack","灰黑色");
        dictionary.put("foamy","泡沫狀的");
        dictionary.put("slimy","黏糊糊的");
        dictionary.put("transparent","透明");
        dictionary.put("milky","乳白色");
        dictionary.put("yellowGreen","黃綠色");
        dictionary.put("pink","粉色");
        dictionary.put("brown","褐色");
        dictionary.put("black","黑色");
        dictionary.put("waterLike","水樣狀");
        dictionary.put("stickyPus","黏膿");
        return dictionary;
    }
}
