package com.example.recyclerviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder>{

    private static final String TAG = "CheckBoxAdapter";

    private Context context;

    private List<Test2.InfoBean> infoBeanList2= new ArrayList<>();

    public CheckBoxAdapter(Context context, List<Test2.InfoBean> infoBeanList2) {
        this.context = context;
        this.infoBeanList2 = infoBeanList2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disease_checkbox_item, parent, false);
        return new CheckBoxAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          String[] str = infoBeanList2.get(position).getKey().split(",");  //以,切割

          holder.tvTitle.setText(str[0]);     //痰.鼻涕
          holder.tvTitleSub.setText(str[1]);  //顏色,型態

          CheckBoxSubAdapter adapter = new CheckBoxSubAdapter(context, infoBeanList2.get(position).getValue());
          holder.subRecycler.setAdapter(adapter);
          holder.subRecycler.setHasFixedSize(true);
          holder.subRecycler.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    public int getItemCount() {
        return infoBeanList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvTitleSub;
        RecyclerView subRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvSympTitlle);
            tvTitleSub = itemView.findViewById(R.id.tvSympSub);
            subRecycler = itemView.findViewById(R.id.rvSub);
        }
    }
}
