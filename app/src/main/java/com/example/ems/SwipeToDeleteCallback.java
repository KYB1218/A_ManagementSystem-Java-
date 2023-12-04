package com.example.ems;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private final ListAdapter mAdapter;

    public SwipeToDeleteCallback(ListAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.showDeleteButton(position);

        // 삭제 버튼을 클릭하면 해당 아이템을 삭제
        mAdapter.deleteItem(position);
    }

    // 삭제 버튼을 다시 숨기는 메서드
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        int position = viewHolder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION && position < mAdapter.getItemCount()) {
            mAdapter.hideDeleteButton(position);
        }
    }
}
