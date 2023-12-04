package com.example.ems;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.veiwHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private RecyclerView recyclerView;

    private ArrayList<Equipment_item> equipItems;
    // ItemTouchHelper 객체 선언
    private ItemTouchHelper itemTouchHelper;

    public ListAdapter(ArrayList<Equipment_item> equipItems, RecyclerViewInterface recyclerViewInterface, RecyclerView recyclerView) {
        this.equipItems = equipItems;
        this.recyclerViewInterface = recyclerViewInterface;
        this.recyclerView = recyclerView; // recyclerView 초기화
        // ItemTouchHelper 객체 초기화
        itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(this));
        // RecyclerView에 ItemTouchHelper를 연결
        itemTouchHelper.attachToRecyclerView(this.recyclerView);
    }


    @NonNull
    @Override
    public ListAdapter.veiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new veiwHolder(holder, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.veiwHolder holder, int position) {
        holder.tv_id.setText(equipItems.get(position).getId());
        holder.tv_name.setText(equipItems.get(position).getName());
        holder.tv_state.setText(equipItems.get(position).getState());

        // 삭제 버튼 클릭 이벤트 처리
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                deleteItem(position);
            }

            public void deleteItem(int position) {
                equipItems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {
        return equipItems.size();
    }

    public void showDeleteButton(int position) {
        equipItems.get(position).setDeleteButtonVisible(true);
        notifyItemChanged(position);
    }

    public void hideDeleteButton(int position) {
        if (position >= 0 && position < equipItems.size()) {
            equipItems.get(position).setDeleteButtonVisible(false);
            notifyItemChanged(position);
        }
    }

    public void deleteItem(int position) {
        equipItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public class veiwHolder extends RecyclerView.ViewHolder {

        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_state;
        Button btnDelete;


        public veiwHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_state = itemView.findViewById(R.id.tv_state);
            btnDelete = itemView.findViewById(R.id.btnDelete);

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
