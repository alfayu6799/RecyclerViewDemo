package com.example.recyclerviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckBoxSubAdapter extends RecyclerView.Adapter<CheckBoxSubAdapter.ViewHolder>{
    private static final String TAG = "CheckBoxSubAdapter";

    private Context context;
    private List<String> value;

    public CheckBoxSubAdapter(Context context, List<String> value) {
        this.context = context;
        this.value = value;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disase_checkbox_sub_item, parent, false);
        return new CheckBoxSubAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder: " + value.get(position));
        holder.checkBox.setText(value.get(position));
    }

    @Override
    public int getItemCount() {
        return value.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
