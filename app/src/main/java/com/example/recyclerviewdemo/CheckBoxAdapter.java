package com.example.recyclerviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder>{

    private static final String TAG = "CheckBoxAdapter";

    private Context context;

    private List<TestData.CheckBoxGroup> checkBoxGroupList = new ArrayList<>();

    public CheckBoxAdapter(Context context, List<TestData.CheckBoxGroup> checkBoxGroupList) {
        this.context = context;
        this.checkBoxGroupList = checkBoxGroupList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.disease_checkbox_item, parent, false);
        return new CheckBoxAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dictionary dictionary = getDictionary();

        String[] str = checkBoxGroupList.get(position).getKey().split(",");
        String title = str[0];
        String titleSub = str[1];

        holder.tvTitle.setText((CharSequence) dictionary.get(title));
        holder.tvTitleSub.setText((CharSequence) dictionary.get(titleSub));

        CheckBoxSubAdapter adapter = new CheckBoxSubAdapter(context, checkBoxGroupList.get(position).getValue(),position, checkBoxGroupList);
        holder.subRecycler.setAdapter(adapter);
        holder.subRecycler.setHasFixedSize(true);
        holder.subRecycler.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    public int getItemCount() {
        return checkBoxGroupList.size();
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

    private Dictionary getDictionary(){
        Dictionary dictionary = new Hashtable();
        dictionary.put("sputum","???");
        dictionary.put("nose","??????");
        dictionary.put("color","??????");
        dictionary.put("type","??????");

        return dictionary;
    }
}
