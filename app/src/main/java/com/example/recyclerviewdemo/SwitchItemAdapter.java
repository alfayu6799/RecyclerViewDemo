package com.example.recyclerviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SwitchItemAdapter extends RecyclerView.Adapter<SwitchItemAdapter.ViewHolder>{

    private static final String TAG = "SwitchItemAdapter";

    private Context context;
    private List<Test1.InfoBean> infoBeanList = new ArrayList<>();

    private List<TestData.SwitchItemBean> switchItemBeanList = new ArrayList<>();

    public SwitchItemAdapter(Context context, List<Test1.InfoBean> infoBeanList) {
        this.context = context;
        this.infoBeanList = infoBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disease_switch_item, parent, false);
        return new SwitchItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(infoBeanList.get(position).getKey());
        holder.aSwitch.setChecked(infoBeanList.get(position).isValue());
        holder.aSwitch.setText("No");
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d(TAG, "onCheckedChanged: " + infoBeanList.get(position).getKey());
                    holder.aSwitch.setText("Yes");
                }else {
                    holder.aSwitch.setText("No");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        Switch aSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.tvSymptomItem);
            aSwitch = itemView.findViewById(R.id.swSymptom);
        }
    }
}
