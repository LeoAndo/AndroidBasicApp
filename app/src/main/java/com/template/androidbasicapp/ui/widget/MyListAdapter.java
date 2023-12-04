package com.template.androidbasicapp.ui.widget;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.data.Item;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.VH> {
    @NonNull
    private final List<Item> items;
    @Nullable
    private OnItemClickListener listener;

    public MyListAdapter(@NonNull final List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final Item currentItem = items.get(position);
        holder.textTitle.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void updateItem(final int position) {
        if (getItemCount() <= 0) return;

        final Item updateItem = items.get(position);
        updateItem.setTitle("update: " + updateItem.getTitle());
        notifyItemChanged(position);
    }

    public void insertItem(final int position, @NonNull final Item newItem) {
        items.add(position, newItem);
        notifyItemInserted(position); // 変更がある箇所だけ差分更新する.
        // notifyDataSetChanged(); // これだとリスト全体の更新が走る.
    }

    public void removeItem(final int position) {
        if (getItemCount() <= 0) return;

        items.remove(position);
        notifyItemRemoved(position);
    }

    class VH extends RecyclerView.ViewHolder {
        private static final String TAG = "VH";
        private final TextView textTitle;

        public VH(@NonNull final View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                Log.d(TAG, "onClick position-> " + position);
                if (position != NO_POSITION && listener != null) {
                    listener.onItemClick(items.get(position), position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull final Item item, final int position);
    }

    public void setOnItemClickListener(@Nullable final OnItemClickListener l) {
        this.listener = l;
    }
}