package com.example.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SwitchItemAdapter extends RecyclerView.Adapter<SwitchItemAdapter.ViewHolder>{

    private Context context;
    ArrayList<Object> booleanArrayList;

    public SwitchItemAdapter(Context context) {
        this.context = context;
    }

//    public SwitchItemAdapter(Context context, List<TestData> testDataList) {
//        this.context = context;
//        this.testDataList = testDataList;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disease_switch_item, parent, false);
        return new SwitchItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.textName.setText("頭痛");
        //holder.aSwitch.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return 0;
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
