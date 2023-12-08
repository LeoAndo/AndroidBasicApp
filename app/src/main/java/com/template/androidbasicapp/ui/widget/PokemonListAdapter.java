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
import com.template.androidbasicapp.data.Pokemon;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.VH> {
    @NonNull
    private final List<Pokemon> items;
    @Nullable
    private OnItemClickListener listener;

    public PokemonListAdapter(@NonNull final List<Pokemon> items) {
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
        final Pokemon currentItem = items.get(position);
        holder.textTitle.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    protected class VH extends RecyclerView.ViewHolder {
        private static final String TAG = "VH";
        private final TextView textTitle;

        private VH(@NonNull final View itemView) {
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
        void onItemClick(@NonNull final Pokemon item, final int position);
    }

    public void setOnItemClickListener(@Nullable final OnItemClickListener l) {
        this.listener = l;
    }
}