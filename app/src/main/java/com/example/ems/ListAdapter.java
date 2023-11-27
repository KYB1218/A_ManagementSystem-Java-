package com.example.ems;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.veiwHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    private ArrayList<Equipment_item> equipItems;

    public ListAdapter(ArrayList<Equipment_item> equipItems, RecyclerViewInterface recyclerViewInterface) {
        this.equipItems = equipItems;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public veiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new veiwHolder(holder, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull veiwHolder holder, int position) {
        holder.tv_id.setText(equipItems.get(position).getId());
        holder.tv_name.setText(equipItems.get(position).getName());
        holder.tv_state.setText(equipItems.get(position).getState());

    }

    @Override
    public int getItemCount() {
        return equipItems.size();
    }

    public class veiwHolder extends RecyclerView.ViewHolder {

        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_state;


        public veiwHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_state = itemView.findViewById(R.id.tv_state);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });

        }

    }
}
