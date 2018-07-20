package com.study.chazo.naverbandanalysis.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.chazo.naverbandanalysis.base.viewmodel.BaseItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chazo on 2018-07-20
 */
public class BindingRecyclerViewAdapter<ViewModel extends BaseItemViewModel> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.BindViewHolder> {
    private List<ViewModel> items = new ArrayList<>();

    public void setItems(List<ViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void addItems(List<ViewModel> items) {
        this.items.addAll(items);
    }

    public void addItem(ViewModel item) {
        items.add(item);
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    public void removeItem(ViewModel item) {
        items.remove(item);
    }

    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new BindViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    static class BindViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        BindViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(BaseItemViewModel viewModel, int position) {
            binding.setVariable(BR.position, position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
        }
    }
}
